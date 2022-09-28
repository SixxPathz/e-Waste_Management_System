package com.example.e_wastemanagementsystem;

public class Orders {
    private String Item,Category,Quantity,Weight,UserID,Customer,DocID;

    public Orders(String item, String category, String quantity, String weight,String userID,String customer,String docID) {
        Item = item;
        Category = category;
        Quantity = quantity;
        Weight = weight;
        UserID = userID;
        DocID = docID;
        Customer =customer;
    }

    public String getDocID() {
        return DocID;
    }

    public void setDocID(String docID) {
        DocID = docID;
    }

    public Orders() {
    }
    public String getCustomer()
    {
        return Customer;
    }
    public void setCustomer(String customer) {
        Customer = customer;
    }

public String getUserID()
{
    return UserID;
}
    public void setUserID(String userID) {
        UserID = userID;
    }
    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }
}
