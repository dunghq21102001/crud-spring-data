package com.BEFresherTP.DTO;

public class CustomerWithInvoice {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    private int invoiceID;
    private String invoiceDescription;
    private Double invoicePrice;
    private Integer invoiceQuantity;

    public CustomerWithInvoice() {
    }

    public CustomerWithInvoice(int customerID, String customerName, String customerAddress, String customerPhone, String customerEmail, int invoiceID, String invoiceDescription, Double invoicePrice, Integer invoiceQuantity) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.invoiceID = invoiceID;
        this.invoiceDescription = invoiceDescription;
        this.invoicePrice = invoicePrice;
        this.invoiceQuantity = invoiceQuantity;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceDescription() {
        return invoiceDescription;
    }

    public void setInvoiceDescription(String invoiceDescription) {
        this.invoiceDescription = invoiceDescription;
    }

    public Double getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(Double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public Integer getInvoiceQuantity() {
        return invoiceQuantity;
    }

    public void setInvoiceQuantity(Integer invoiceQuantity) {
        this.invoiceQuantity = invoiceQuantity;
    }
}
