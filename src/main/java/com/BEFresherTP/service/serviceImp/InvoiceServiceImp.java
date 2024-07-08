package com.BEFresherTP.service.serviceImp;

import com.BEFresherTP.DTO.InvoiceDTO;
import com.BEFresherTP.entity.Invoice;
import com.BEFresherTP.repository.InvoiceRepository;
import com.BEFresherTP.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImp implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(i -> this.modelMapper.map(i, InvoiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoiceById(int id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isPresent()) {
            return this.modelMapper.map(invoice.get(), InvoiceDTO.class);
        } else throw new NoSuchElementException("Invoice not found");
    }

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = this.modelMapper.map(invoiceDTO, Invoice.class);
        invoiceRepository.save(invoice);
        return this.modelMapper.map(invoice, InvoiceDTO.class);
    }
}
