package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.service.impl.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tournament")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTournament() {
        return ResponseEntity.ok(tournamentService.getAllTournament());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveTournament(@Valid @RequestBody TournamentDTO tournamentDTO) {
        return ResponseEntity.ok(tournamentService.saveTournament(tournamentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTournamentById(@PathVariable Long id){
        return ResponseEntity.ok(tournamentService.deleteTournamentById(id));
    }

    //Tournament Users/Participants

    @GetMapping("/{id}/participants")
    public ResponseEntity<?> getTournamentParticipants(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.getTournamentParticipants(id));
    }

    @PostMapping("/{user_id}/{tournament_id}")
    public ResponseEntity<?> addParticipant(@PathVariable Long user_id, @PathVariable Long tournament_id) {
        return ResponseEntity.ok(tournamentService.addParticipant(user_id, tournament_id));
    }
}

