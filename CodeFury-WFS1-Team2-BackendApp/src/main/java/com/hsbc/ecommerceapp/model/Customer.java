package com.hsbc.ecommerceapp.model;

public class Customer extends User {
    private String address, phoneNumber;

    public Customer(String userId, String userName, String password, String email) {
        super(userId, userName, password, email);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
