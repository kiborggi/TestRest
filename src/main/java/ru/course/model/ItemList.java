package ru.course.model;


import org.springframework.stereotype.Component;

import java.io.Serializable;



@Component("itemlist")
public class ItemList implements Serializable {

    public ItemList(int id, Brands BrandId, Group GroupId, String Model,
                    int price,int NumBrand, int NumGroup){
        super();
        this.id=id;
        this.BrandId=BrandId;
        this.GroupId=GroupId;
        this.Model=Model;
        this.Price=price;
        this.NumBrand=NumBrand;
        this.NumGroup=NumGroup;
        this.SetCount=0;



    }

    public ItemList(){


    }


    private int id;
    private int Price;
    private Brands BrandId;
    private Group GroupId;
    private String Model;
    private int NumBrand;

    public int getId() {
        return id;
    }

    private int NumGroup;
    private int SetCount;

    public int getPrice() {
        return Price;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    private  String Picture;


    //set
    public void setNumBrand(int numBrand) {

        this.NumBrand = numBrand;
    }

    public void setNumGroup(int numGroup) {

        this.NumGroup = numGroup;
    }



    public void setModel(String model) {

        this.Model = model;
    }

    public void setId(int ident) {

        this.id = ident;
    }

    public void setPrice(int price) {

        this.Price = price;
    }

    public void setBrandId(Brands brandId) {

        this.BrandId = brandId;

    }

    public void setGroupId(Group brandId) {

        this.GroupId = brandId;

    }

    public void setSetCount(int setCount) {

        this.SetCount = setCount;

    }

    //get
    public int getSetCount() {

        return this.SetCount;
    }

    public int getNumBrand() {

        return this.NumBrand;
    }

    public int getNumGroup() {

        return this.NumGroup;
    }


    public Brands getBrandId() {

        return this.BrandId;
    }

    public Group getGroupId() {

        return this.GroupId;
    }


    public int id() {

        return this.id;
    }

    public int Price() {

        return this.Price;
    }


    public String getModel() {

        return this.Model;
    }



}