package com.BEFresherTP.controller;

import com.BEFresherTP.DTO.CustomerDTO;
import com.BEFresherTP.DTO.CustomerWithInvoice;
import com.BEFresherTP.service.serviceImp.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImp customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PostMapping("/with-invoice")
    public ResponseEntity<CustomerDTO> withInvoice(@RequestBody CustomerWithInvoice customerWithInvoice) {
        CustomerDTO customerDTO = customerService.createCustomerWithFirstInvoice(customerWithInvoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable int id) {
        CustomerDTO createdCustomer = customerService.updateCustomer(customerDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(createdCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully");
    }
}
