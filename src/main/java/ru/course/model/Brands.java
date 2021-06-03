package ru.course.model;


import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component("brands")
public class Brands implements Serializable {

    private static final long serialVersionUID = 2041275512219239990L;

    public Brands(){

        this.id=0;
        this.BrandName="";

    }

    public Brands(int id, String brandName){

        this.id=id;
        this.BrandName=brandName;

    }

    private int id;
    private String BrandName;



    public void setBrandName(String name) {
        this.BrandName = name;
    }

    public void setId(int ident) {
        this.id = ident;
    }

    public int id(){

        return this.id;

    }

    public String BrandName(){

        return this.BrandName;

    }



}