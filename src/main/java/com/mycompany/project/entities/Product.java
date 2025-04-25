





package com.mycompany.project.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {

    // Tables and Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pId;

    private String pName;

    @Column(length = 3000)
    private String pDesc;

    private String pPhoto;
    private int pPrice;
    private int pDiscount;
    private int pQuantity;

    // Many to one mapping
    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    // Constructor without pID
    public Product(String pName, String pDesc, String pPhoto, int pPrice, int pDiscount, int pQuantity, Category category) {
        this.pName = pName;
        this.pDesc = pDesc;
        this.pPhoto = pPhoto;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pQuantity = pQuantity;
        this.category = category;
    }

    // Empty Constructor
    public Product() {
    }

    // Getters and Setters
    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getPDesc() {
        return pDesc;
    }

    public void setPDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getPPhoto() {
        return pPhoto;
    }

    public void setPPhoto(String pPhoto) {
        this.pPhoto = pPhoto;
    }

    public int getPPrice() {
        return pPrice;
    }

    public void setPPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public int getPDiscount() {
        return pDiscount;
    }

    public void setPDiscount(int pDiscount) {
        this.pDiscount = pDiscount;
    }

    public int getPQuantity() {
        return pQuantity;
    }

    public void setPQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    // to calculate price after discount
    public int getPriceAfterDiscount() {
        int d = (int) ((this.getPDiscount() / 100.00) * this.getPPrice());
        return this.getPPrice() - d;
    }

    @Override
    public String toString() {
        return "{" +
                " pId='" + getPId() + "'" +
                ", pName='" + getPName() + "'" +
                ", pDesc='" + getPDesc() + "'" +
                ", pPhoto='" + getPPhoto() + "'" +
                ", pPrice='" + getPPrice() + "'" +
                ", pDiscount='" + getPDiscount() + "'" +
                ", pQuantity='" + getPQuantity() + "'" +
                "}";
    }
}
