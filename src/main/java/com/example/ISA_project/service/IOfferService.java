package com.example.ISA_project.service;

import com.example.ISA_project.model.Offer;

import java.util.List;

public interface IOfferService {

    List<Offer>getAllBySupplier(String email);
}
