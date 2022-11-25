package com.bezkoder.springjwt.service.impl;

import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.models.Tournament;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.TournamentRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.service.ITournamentService;
import com.bezkoder.springjwt.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TournamentService implements ITournamentService {

    private final TournamentRepository tournamentRepository;
    private final UserRepository userRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository, UserRepository userRepository) {
        this.tournamentRepository = tournamentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TournamentDTO> getAllTournament() {
        return ConvertUtils.convertTournamentListToDtoList(tournamentRepository.findAll());
    }

    @Override
    public TournamentDTO saveTournament(TournamentDTO tournamentDTO) {
        Tournament tournament = ConvertUtils.convertToTournament(tournamentDTO);
        return ConvertUtils.convertToTournamentDTO(tournamentRepository.save(tournament));
    }

    @Override
    public Boolean deleteTournamentById(Long id) {
        tournamentRepository.deleteById(id);
        return null;
    }

    @Override
    public Set<User> getTournamentParticipants(Long id) {
        Tournament tournament = tournamentRepository.findById(id).orElse(null);
        if(tournament == null)
            return null;
        return tournament.getUsers();
    }

    @Override
    public TournamentDTO addParticipant(Long user_id, Long tournament_id) {
        Tournament tournament = tournamentRepository.findById(tournament_id).orElse(null);
        if(tournament == null)
            return null;

        User user = userRepository.findById(user_id).orElse(null);
        if(user == null)
            return null;

        tournament.addUser(user);
        return ConvertUtils.convertToTournamentDTO(tournamentRepository.save(tournament));
    }


}
