package com.bezkoder.springjwt.service.impl;

import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.models.Tournament;
import com.bezkoder.springjwt.repository.TournamentRepository;
import com.bezkoder.springjwt.service.ITournamentService;
import com.bezkoder.springjwt.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService implements ITournamentService {

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<TournamentDTO> getAllTournament() {
        return ConvertUtils.convertTournamentListToDtoList(tournamentRepository.findAll());
    }

    @Override
    public TournamentDTO saveTournament(TournamentDTO tournamentDTO) {
        Tournament tournament = ConvertUtils.convertToTournament(tournamentDTO);
        return ConvertUtils.convertToTournamentDTO(tournamentRepository.saveAndFlush(tournament));
    }

    @Override
    public Boolean deleteTournamentById(int id) {
        tournamentRepository.deleteById(id);
        return null;
    }
}
