package ru.course.dao.products;

import ru.course.dao.products.interfaces.*;

public class DAO_Factory {


    public static IBrandDAO getItemBrandDAO() {

        return new ItemBrandDAO();
    }

    public static IGroupDAO getItemGroupDAO() {

        return new ItemGroupDAO();
    }

    public static I_ItemDAO getItemListDAO() {

        return new ItemListDAO();
    }

    public static I_DetailedOrdersDAO getDetailedOrdersDAO() {

        return new DetailedOrdersDAO();
    }

    public static I_OrderDAO getOrdersDAO() {

        return new OrdersDAO();
    }




}