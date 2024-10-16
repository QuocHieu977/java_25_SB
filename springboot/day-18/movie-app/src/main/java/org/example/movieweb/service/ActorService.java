package org.example.movieweb.service;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.repository.ActorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;

    public Object getAllActors() {
        return actorRepository.findAll();
    }
}
