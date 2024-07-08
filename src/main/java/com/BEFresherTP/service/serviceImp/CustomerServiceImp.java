package com.BEFresherTP.service.serviceImp;

import com.BEFresherTP.DTO.CustomerDTO;
import com.BEFresherTP.DTO.CustomerWithInvoice;
import com.BEFresherTP.DTO.InvoiceDTO;
import com.BEFresherTP.entity.Customer;
import com.BEFresherTP.entity.Invoice;
import com.BEFresherTP.repository.CustomerRepository;
import com.BEFresherTP.service.CustomerService;
import com.BEFresherTP.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InvoiceServiceImp invoiceService;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return this.modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO createCustomerWithFirstInvoice(CustomerWithInvoice customerWithInvoice) {
        Customer customer = new Customer(customerWithInvoice.getCustomerName(),
                customerWithInvoice.getCustomerAddress(),
                customerWithInvoice.getCustomerPhone(),
                customerWithInvoice.getCustomerEmail());
        Customer savedCustomer = customerRepository.save(customer);

        Integer customerId = savedCustomer.getId();
        Invoice invoice = new Invoice(customerWithInvoice.getInvoiceDescription(),
                customerWithInvoice.getInvoicePrice(),
                customerWithInvoice.getInvoiceQuantity(),
                savedCustomer);

        invoiceService.addInvoice(this.modelMapper.map(invoice, InvoiceDTO.class));


        Customer finalCustomer = customerRepository.findById(customerId).get();
        return this.modelMapper.map(finalCustomer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(cus -> this.modelMapper.map(cus, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
            return customerDTO;
        } else throw new NoSuchElementException("Customer not found customer with id=" + id);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, int id) {
        Customer customer = customerRepository.findById(id).get();
        customer.setName(customerDTO.getCustomerName());
        customer.setAddress(customerDTO.getCustomerAddress());
        customer.setEmail(customerDTO.getCustomerEmail());
        customer.setPhone(customerDTO.getCustomerPhone());
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Override
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }
}
