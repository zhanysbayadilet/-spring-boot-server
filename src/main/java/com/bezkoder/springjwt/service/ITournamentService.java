package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.dto.UserDTO;
import com.bezkoder.springjwt.models.UserTournament;
import com.bezkoder.springjwt.util.PageableCustom;

import java.util.List;
import java.util.Map;

public interface ITournamentService {

    PageableCustom getAllTournament(Map<String, String> params);

    TournamentDTO saveTournament(TournamentDTO tournamentDTO);

    Boolean deleteTournamentById(Long id);

    TournamentDTO getTournamentById(Long id);

    List<UserDTO> getTournamentParticipants(Long id);

    TournamentDTO addParticipant(Long user_id, Long tournament_id);

    Integer getCountTournaments();

    UserTournament checkStatusSubscribe(Long userId, Long tournamentId);

    Integer unsubscribeToTournament(Long userId, Long tournamentId);
}
