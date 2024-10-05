package org.example.movieweb.service;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.repository.DirectorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;

    public Object getAllDirectors() {
        return directorRepository.findAll();
    }
}
