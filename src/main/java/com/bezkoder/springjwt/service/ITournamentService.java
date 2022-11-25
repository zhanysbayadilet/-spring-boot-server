package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.models.User;

import java.util.List;
import java.util.Set;

public interface ITournamentService {

    List<TournamentDTO> getAllTournament();

    TournamentDTO saveTournament(TournamentDTO tournamentDTO);

    Boolean deleteTournamentById(Long id);

    Set<User> getTournamentParticipants(Long id);

    TournamentDTO addParticipant(Long user_id, Long tournament_id);
}
