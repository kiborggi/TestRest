package ru.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.course.dao.AppUserDAO;
import ru.course.dao.products.DAO_Factory;
import ru.course.dao.products.interfaces.I_DetailedOrdersDAO;
import ru.course.dao.products.interfaces.I_ItemDAO;
import ru.course.dao.products.interfaces.I_OrderDAO;
import ru.course.model.AppUser;
import ru.course.model.DetailedOrders;
import ru.course.model.Item;
import ru.course.model.Orders;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller @RequestMapping(value = { "/admin", })


public class AdminController {

    @Autowired
    private AppUserDAO appUserDAO;

    private final I_ItemDAO i_itemDAO= DAO_Factory.getItemListDAO();
    private final I_OrderDAO orderDAO = DAO_Factory.getOrdersDAO();
    private final I_DetailedOrdersDAO detailedOrdersDAO = DAO_Factory.getDetailedOrdersDAO();

    @GetMapping("/")
    public String adminka()  {

    return "Admin/ControlPanel";
    }

    @GetMapping("/Users")
    public String Users(Model model)  {

       model.addAttribute("users",appUserDAO.getAllUsers());

        return "Admin/Users";
    }
    @GetMapping("/Orders")
    public String Orders(Model model)  {
        List<Orders> ordersList = new ArrayList<>();

        try {
            ordersList = orderDAO.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("orderList", ordersList);
        return "Admin/Orders";
    }
    @GetMapping("/Items")
    public String Items(Model model)  {
        List<Item> itemList = new ArrayList<>();
        try {
            itemList = i_itemDAO.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("itemList", itemList);

        return "Admin/Items";
    }


    @GetMapping("/OrderDetails/{id}")
    protected String OrderDetails (@PathVariable("id") int orderId, Model model, HttpServletRequest req )
            throws SQLException {


        Orders order = orderDAO.getByPK(orderId);


        int totalPrice = 0;

        List<DetailedOrders> detailedOrdersList = detailedOrdersDAO.getByOrderId(orderId);

        for(DetailedOrders  d : detailedOrdersList){
            totalPrice += d.Price();
        }
        model.addAttribute("order",order);
        model.addAttribute("detailedOrdersList",detailedOrdersList);
        model.addAttribute("totalPrice", totalPrice);
        return "Admin/orderDetails";
    }



    @GetMapping("/UserDetails/{id}")
    protected String UserDetails (@PathVariable("id") int userID, Model model, HttpServletRequest req )
            throws SQLException {


          AppUser appUser =  appUserDAO.findUserById((long)userID);
        model.addAttribute("user", appUser);
        return "Admin/Userdetals";
    }
    @PostMapping("/EditOrder")
    protected String EditOrder (@RequestParam ("id") String id, @RequestParam ("string") String string ) throws SQLException {

        Orders orders = orderDAO.getByPK(Integer.parseInt(id));
        orders.setStatus(string);
        orderDAO.update(orders,Integer.parseInt(id));
        return "redirect:/admin/Orders";
    }

}
