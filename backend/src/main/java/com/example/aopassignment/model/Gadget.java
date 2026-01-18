package com.example.aopassignment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gadgets")
public class Gadget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GadgetType type;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GadgetStatus status;

    public Gadget() {
    }

    public Gadget(Long id, String name, String brand, GadgetType type, Double price, GadgetStatus status) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public GadgetType getType() {
        return type;
    }

    public void setType(GadgetType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public GadgetStatus getStatus() {
        return status;
    }

    public void setStatus(GadgetStatus status) {
        this.status = status;
    }

    public enum GadgetType {
        PHONE, LAPTOP, TABLET, WEARABLE, ACCESSORY
    }

    public enum GadgetStatus {
        AVAILABLE, OUT_OF_STOCK, DISCONTINUED
    }
}
