package ru.course.model.Surveys;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;


    String name;


    public boolean eqName(String name){
        if (this.name == null){
            return false;
        }
        else
            return this.name.equals(name);
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category() {
    }
}
