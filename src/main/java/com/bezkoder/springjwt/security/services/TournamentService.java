package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Tournament;
import com.bezkoder.springjwt.repository.TournamentRepository;
import com.bezkoder.springjwt.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    public Tournament findOne(int id) {
        Optional<Tournament> foundTournament = tournamentRepository.findById(id);
        return foundTournament.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void delete(int id) {
        tournamentRepository.deleteById(id);
    }

    @Transactional
    public void save(Tournament tournament) {
        tournamentRepository.save(tournament);
    }
}
