package org.example.movieweb.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.Episode;
import org.example.movieweb.model.request.CreateEpisodeRequest;
import org.example.movieweb.model.request.UpdateEpisodeRequest;
import org.example.movieweb.service.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/episodes")
@RequiredArgsConstructor
public class EpisodeApi {
    private final EpisodeService episodeService;

    @PostMapping
    public ResponseEntity<?> createEpisode(@Valid @RequestBody CreateEpisodeRequest request) {
        Episode episode = episodeService.createEpisode(request);
        return ResponseEntity.ok(episode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEpisode(@RequestBody UpdateEpisodeRequest request,
                                          @PathVariable Integer id) {
        Episode episode = episodeService.updateEpisode(id, request);
        return ResponseEntity.ok(episode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEpisode(@PathVariable Integer id) {
        episodeService.deleteEpisode(id);
        return ResponseEntity.ok().build();
    }
}
