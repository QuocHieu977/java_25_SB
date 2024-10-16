package org.example.movieweb.service;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.Country;
import org.example.movieweb.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
