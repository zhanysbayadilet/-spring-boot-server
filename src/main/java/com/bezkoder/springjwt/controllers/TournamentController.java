package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.models.UserTournament;
import com.bezkoder.springjwt.service.impl.TournamentService;
import com.bezkoder.springjwt.util.PageableCustom;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/tournament")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/all")
    public ResponseEntity<PageableCustom> getAllTournament(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(tournamentService.getAllTournament(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTournamentById(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.getTournamentById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveTournament(@Valid @RequestBody TournamentDTO tournamentDTO) {
        return ResponseEntity.ok(tournamentService.saveTournament(tournamentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTournamentById(@PathVariable Long id){
        return ResponseEntity.ok(tournamentService.deleteTournamentById(id));
    }

    //Tournament Participants

    @GetMapping("/{tournament_id}/participants")
    public ResponseEntity<?> getTournamentParticipants(@PathVariable Long tournament_id) {
        return ResponseEntity.ok(tournamentService.getTournamentParticipants(tournament_id));
    }

    @PostMapping("/{user_id}/{tournament_id}")
    public ResponseEntity<?> addParticipant(@PathVariable Long user_id, @PathVariable Long tournament_id) {
        return ResponseEntity.ok(tournamentService.addParticipant(user_id, tournament_id));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCountTournaments(){
        return ResponseEntity.ok(tournamentService.getCountTournaments());
    }

    @GetMapping("/userId/{userId}/tournamentId/{tournamentId}")
    public ResponseEntity<UserTournament> checkStatusSubscribe(@PathVariable Long userId,
                                                               @PathVariable Long tournamentId){
        return ResponseEntity.ok(tournamentService.checkStatusSubscribe(userId, tournamentId));
    }

    @DeleteMapping("/userId/{userId}/tournamentId/{tournamentId}/unsubscribe")
    public ResponseEntity<Integer> unsubscribeToTournament(@PathVariable Long userId, @PathVariable Long tournamentId){
        return ResponseEntity.ok(tournamentService.unsubscribeToTournament(userId, tournamentId));
    }
}

