package ru.course.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.course.dao.AppUserDAO;

import ru.course.model.AppUser;


import ru.course.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.management.openmbean.CompositeData;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired
    private AppUserDAO appUserDAO;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;




    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        System.out.println(appUserDAO.findUserAccount(loginedUser.getUsername()).getUserId());
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }


    @RequestMapping(value = "/register", method=RequestMethod.GET)
    public String registr(Model model) {
        AppUser foo = new AppUser();
        foo.setUserName("bar");

        model.addAttribute("foo", foo);
        return "register";
    }

    @RequestMapping(value = "/processForm", method=RequestMethod.POST)
    public String processForm(@ModelAttribute(value="foo") AppUser foo) {

        foo.setEncrytedPassword(passwordEncoder.encode(foo.getEncrytedPassword()));
        System.out.println(foo.getUserName());
        System.out.println(foo.getEncrytedPassword());


        appUserDAO.addUser(foo);
        return "welcomePage";
    }


}