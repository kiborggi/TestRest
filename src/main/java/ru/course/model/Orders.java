package ru.course.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

public class Orders {


    //constructors
    public Orders(){

        this.Id=0;
        this.OrderCode=0;
        this.UserId=0;
        this.Status="in progress";

    }

    public Orders(int Id, int OrderCode, int UserId,String Status){

        this.Id=Id;
        this.OrderCode=OrderCode;
        this.UserId=UserId;
        this.Status=Status;


    }


    //fields
    private int Id;
    private int UserId;
    private int OrderCode; // "unique" number of the order
    private String Status;


    //set
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public void setOrderCode(int OrderCode){

        this.OrderCode=OrderCode;

    }

    public void setStatus(String Status){

        this.Status=Status;

    }


    //get
    public int getId() {
        return this.Id;
    }

    public int getUserId() {
        return this.UserId;
    }

    public int getOrderCode(){

        return this.OrderCode;

    }

    public String getStatus(){

        return this.Status;

    }

}