package com.example.atp;

public class Product {
    private long id;
    private String name;
    private Double value;

    public Product(long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Product(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

