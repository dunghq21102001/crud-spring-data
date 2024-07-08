package com.BEFresherTP.service;

import com.BEFresherTP.DTO.InvoiceDTO;
import com.BEFresherTP.entity.Invoice;
import com.BEFresherTP.repository.InvoiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface InvoiceService {



    public List<InvoiceDTO> getAllInvoices();

    public InvoiceDTO getInvoiceById(int id);

    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

}
