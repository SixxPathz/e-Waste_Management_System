package com.example.e_wastemanagementsystem;

public class User {

    String item, category, quantity, weight, userID, customer,docID;

    public User() {
    }

    public User(String item, String category, String quantity, String weight, String userID, String customer, String docID) {
        this.item = item;
        this.category = category;
        this.quantity = quantity;
        this.weight = weight;
        this.userID = userID;
        this.customer = customer;
        this.docID = docID;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
