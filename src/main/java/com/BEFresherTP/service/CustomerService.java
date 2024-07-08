package com.BEFresherTP.service;

import com.BEFresherTP.DTO.CustomerDTO;
import com.BEFresherTP.DTO.CustomerWithInvoice;
import com.BEFresherTP.DTO.InvoiceDTO;
import com.BEFresherTP.entity.Customer;
import com.BEFresherTP.entity.Invoice;
import com.BEFresherTP.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface CustomerService {



    public CustomerDTO createCustomer(CustomerDTO customerDTO);

    @Transactional
    public CustomerDTO createCustomerWithFirstInvoice(CustomerWithInvoice customerWithInvoice);

    public List<CustomerDTO> getAllCustomers();

    public CustomerDTO getCustomerById(int id);

    public CustomerDTO updateCustomer(CustomerDTO customerDTO, int id);

    public void deleteCustomerById(int id);
}
