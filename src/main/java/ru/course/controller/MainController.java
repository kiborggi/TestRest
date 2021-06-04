package ru.course.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.course.dao.AppUserDAO;
import ru.course.dao.products.DAO_Factory;
import ru.course.dao.products.interfaces.*;
import ru.course.model.AppUser;


import ru.course.model.Item;
import ru.course.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.management.openmbean.CompositeData;

@Controller
public class MainController {
    @Autowired
    private AppUserDAO appUserDAO;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private final I_ItemDAO i_itemDAO= DAO_Factory.getItemListDAO();
    private final I_OrderDAO orderDAO = DAO_Factory.getOrdersDAO();
    private final I_DetailedOrdersDAO detailedOrdersDAO = DAO_Factory.getDetailedOrdersDAO();
    private final IBrandDAO iBrandDAO = DAO_Factory.getItemBrandDAO();
    private final IGroupDAO iGroupDAO = DAO_Factory.getItemGroupDAO();

    @RequestMapping(value = { "/",  }, method = RequestMethod.GET)
    public String GetItems(Model model) throws SQLException {
        model.addAttribute("groupList",iGroupDAO.getAll());

        model.addAttribute("brandList",iBrandDAO.getAll());

        model.addAttribute("ItemsList", i_itemDAO.getAll());
        return "Shop/ItemsList";
    }

    @RequestMapping(value = {  "/items" }, method = RequestMethod.GET)
    public String parseItems(Model model, @RequestParam(required = false) String brand, @RequestParam(required = false) String category, @RequestParam(required = false) String min_price, @RequestParam(required = false) String max_price) throws SQLException {
        model.addAttribute("groupList",iGroupDAO.getAll());

        model.addAttribute("brandList",iBrandDAO.getAll());

        model.addAttribute("ItemsList", i_itemDAO.getAll());
        List<Item> list = i_itemDAO.getAll();
        for(Item i : list){
            System.out.println(i.getGroupId().SectionName());
        }


        if(min_price != null && !min_price.equals("")) {



            list.removeIf(item -> item.getPrice() < Integer.parseInt(min_price));
        }
        if (max_price != null && !max_price.equals("")){
            list.removeIf(item -> item.getPrice() > Integer.parseInt(max_price));
        }
        if ( brand != null && !brand.equals("")){
            list.removeIf(item -> !item.getBrandId().BrandName().equals((brand)));
        } if(category != null && !category.equals("")) {
            System.out.println(category);
            list.removeIf(item -> !item.getGroupId().SectionName().equals(category));
        }

        model.addAttribute("ItemsList", list);
        return "Shop/ItemsList";
    }

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