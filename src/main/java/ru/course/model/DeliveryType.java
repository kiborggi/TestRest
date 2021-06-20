package ru.course.model;

public class DeliveryType {

    private int id;
    private String name;

    public DeliveryType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeliveryType(int id) {
        this.id = id;
    }
}
