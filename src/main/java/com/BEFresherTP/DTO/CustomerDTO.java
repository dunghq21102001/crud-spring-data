package com.BEFresherTP.DTO;

import com.BEFresherTP.entity.Invoice;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CustomerDTO {
    private int customerID;
    @NotBlank(message = "Customer name can't be blank")
    @Size(min = 3, max = 255, message = "Customer name should be between 3 and 255 characters")
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    private List<Invoice> invoices;

    public CustomerDTO() {
    }

    public CustomerDTO(int customerID, String customerName, String customerAddress, String customerPhone, String customerEmail, List<Invoice> invoices) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.invoices = invoices;
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

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
