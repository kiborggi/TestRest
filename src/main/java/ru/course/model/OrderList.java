package ru.course.model;


import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class OrderList implements Serializable {

    public OrderList(){

        this.detailedOrdersList =new ArrayList<>();
    }


    private List<DetailedOrders> detailedOrdersList;

    public void setDetailedOrdersList(List<DetailedOrders> ord) {

        this.detailedOrdersList = ord;
    }

    public List<DetailedOrders> getDetailedOrdersList() {

        return this.detailedOrdersList;
    }

}