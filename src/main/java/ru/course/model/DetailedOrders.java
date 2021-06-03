package ru.course.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;


public class DetailedOrders implements Serializable {




    public DetailedOrders(int id, ItemList model, int counts, int price,int OrderId){

        this.id=id;
        this.model=model;
        this.Counts=counts;
        this.Price=price;
        this.OrderId=OrderId;


    }

    public DetailedOrders(){

        this.id=0;
        this.model=null;
        this.Counts=0;
        this.Price=0;
        this.OrderId=0;

    }

    private int id;
    private ItemList model;
    private int Counts;
    private int Price;
    private int OrderId;


//set


    public void setId(int ident) {

        this.id = ident;
    }

    public void setOrderId(int OrderId) {

        this.OrderId = OrderId;
    }

    public void setPrice(int price) {

        this.Price = price;
    }


    public void setModel(ItemList item) {

        this.model = item;
    }

    public void setCounts(int counts) {

        this.Counts = counts;
    }

    //get



    public int getId() {

        return this.id;
    }

    public int getOrderId() {

        return this.OrderId;
    }

    public ItemList Model() {

        return this.model;
    }

    public int getCounts() {

        return this.Counts;
    }


    public int Price() {

        return this.Price;
    }


}