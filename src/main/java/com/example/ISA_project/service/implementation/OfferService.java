package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Offer;
import com.example.ISA_project.model.Supplier;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.OfferRepository;
import com.example.ISA_project.service.IOfferService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService implements IOfferService {

    private final OfferRepository offerRepository;
    private final  SupplierService supplierService;
    public OfferService(OfferRepository offerRepository, SupplierService supplierService){this.offerRepository = offerRepository; this.supplierService = supplierService;}

    @Override
    public List<Offer> getAllBySupplier(String email) {
        List<Offer> offers = new ArrayList<>();
        Supplier supplier = supplierService.getByEmailSupplier(email);
        for(Offer offer : offerRepository.findAllBySupplier(supplier)){
                offers.add(offer);
        }
        return offers;
    }
}
