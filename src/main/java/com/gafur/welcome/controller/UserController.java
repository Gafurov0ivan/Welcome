package com.gafur.welcome.controller;

import com.gafur.welcome.model.User;
import com.gafur.welcome.service.AuthenticationService;
import com.gafur.welcome.service.UserService;
import com.gafur.welcome.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Main controller to manage all operations
 *
 * @author igafurov
 * @since 01.11.2016
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "sign-up";
    }

    /**
     * Control user registration operations
     *
     * @param userForm      user form
     * @param bindingResult result to check errors
     * @param model         model
     * @param response      standard request
     * @return redirecting to welcome url
     */
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, HttpServletResponse response) {
        String userName = userForm.getUsername();
        userForm.setUsername(userName.replaceAll("\\s+", ""));
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "sign-up";
        }
        Cookie cookie = new Cookie("cookieName", userName);
        cookie.setMaxAge(2500000);
        response.addCookie(cookie);

        userService.save(userForm);
        authenticationService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/welcome";
    }

    /**
     * Control user authentication and authorisation operations
     *
     * @param model   model
     * @param logout  logout from application
     * @param error   error indicator
     * @param request standard request
     * @return redirecting url
     */
    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String login(Model model, String error, String logout, HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String cookieUserName = null;
        if (request.getCookies() != null) {
            Cookie[] requestCookies = request.getCookies();
            for (Cookie cookie : requestCookies) {
                if (cookie.getName().equalsIgnoreCase("cookieName")) {
                    cookieUserName = cookie.getValue();
                }
            }
        }
        model.addAttribute("cookieUserName", cookieUserName);
        if (principal instanceof UserDetails) {
            return "redirect:/welcome";
        }
        if (error != null) {
            model.addAttribute("error", "Username and password do not match");
        }
        if (logout != null) {
            model.addAttribute("message", "You quit from application");
        }
        return "sign-in";
    }

    /**
     * Redirect url and set a welcome message
     *
     * @param model model
     * @return redirecting url
     */
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {

        LocalTime hour = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        String time = null;
        if (hour.getHour() >= 6 && hour.getHour() < 10) {
            time = "morning";
        }
        if (hour.getHour() >= 10 && hour.getHour() < 18) {
            time = "day";
        }
        if (hour.getHour() >= 18 && hour.getHour() < 22) {
            time = "evening";
        }
        if ((hour.getHour() >= 22 && (hour.getHour() < 6) || hour.getHour() >= 0)) {
            time = "night";
        }
        String dayTime = "Good " + time;
        model.addAttribute("dayTime", dayTime);
        return "welcome";
    }
}