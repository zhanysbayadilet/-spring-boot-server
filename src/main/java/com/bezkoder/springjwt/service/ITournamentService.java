package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.TournamentDTO;

import java.util.List;

public interface ITournamentService {

    List<TournamentDTO> getAllTournament();

    TournamentDTO saveTournament(TournamentDTO tournamentDTO);

    Boolean deleteTournamentById(int id);
}
