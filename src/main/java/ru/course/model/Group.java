package ru.course.model;


import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component("group")
public class Group implements Serializable {


    public Group(int id, String SectionName){

        this.id=id;
        this.SectionName=SectionName;

    }

    public Group(){

        this.id=0;
        this.SectionName="";

    }

    private int id;
    private String SectionName;



    public void setSectionName(String name) {
        this.SectionName = name;
    }

    public void setId(int ident) {
        this.id = ident;
    }

    public int id(){

        return this.id;

    }

    public String SectionName(){

        return this.SectionName;

    }











}
