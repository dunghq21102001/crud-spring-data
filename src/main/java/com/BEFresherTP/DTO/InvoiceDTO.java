package com.BEFresherTP.DTO;

import com.BEFresherTP.entity.Customer;

public class InvoiceDTO {
    private int invoiceID;
    private String description;
    private Double price;
    private Integer quantity;
    private Customer customer;

    public InvoiceDTO() {}

    public InvoiceDTO(int invoiceID, String description, Double price, Integer quantity) {
        this.invoiceID = invoiceID;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public InvoiceDTO(int invoiceID, String description, Double price, Integer quantity, Customer customer) {
        this.invoiceID = invoiceID;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.customer = customer;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
