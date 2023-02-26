package com.bezkoder.springjwt.service.impl;

import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.dto.UserDTO;
import com.bezkoder.springjwt.models.Tournament;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.models.UserTournament;
import com.bezkoder.springjwt.repository.TournamentRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.repository.UserTournamentRepository;
import com.bezkoder.springjwt.service.ITournamentService;
import com.bezkoder.springjwt.util.ConvertUtils;
import com.bezkoder.springjwt.util.PageableCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TournamentService implements ITournamentService {

    private final TournamentRepository tournamentRepository;
    private final UserRepository userRepository;
    private final UserTournamentRepository userTournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository,
                             UserRepository userRepository,
                             UserTournamentRepository userTournamentRepository) {
        this.tournamentRepository = tournamentRepository;
        this.userRepository = userRepository;
        this.userTournamentRepository = userTournamentRepository;
    }

    @Override
    public PageableCustom getAllTournament(Map<String, String> params) {
        int pageNumber = 0;
        int pageSize = 10;
//        String searchText = "";

        for (Map.Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "pageNumber":
                    pageNumber = Integer.parseInt(entry.getValue());
                    break;
                case "pageSize":
                    pageSize = Integer.parseInt(entry.getValue());
                    break;
//                case "searchText":
//                    searchText = entry.getValue();
//                    break;
            }
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Tournament> resultPage;

        resultPage = tournamentRepository.findAll(pageable);

        PageableCustom pageableCustom = new PageableCustom();
        pageableCustom.setTotal(resultPage.getTotalElements());
        pageableCustom.setPage(resultPage.getNumber());
        pageableCustom.setSize(resultPage.getSize());
        pageableCustom.setContent(resultPage.getContent());

        return pageableCustom;
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
    public TournamentDTO getTournamentById(Long id) {
        return ConvertUtils.convertToTournamentDTO(tournamentRepository.findById(id).orElseThrow());
    }

    @Override
    public List<UserDTO> getTournamentParticipants(Long tournament_id) {
        Tournament tournament = tournamentRepository.findById(tournament_id).orElse(null);
        if(tournament == null)
            return null;
        return ConvertUtils.convertUserListToDtoList(tournament.getUsers());
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

    @Override
    public Integer getCountTournaments() {
        return tournamentRepository.getCountTournaments();
    }

    @Override
    public UserTournament checkStatusSubscribe(Long userId, Long tournamentId) {
        return userTournamentRepository.findByUserIdAndTournamentId(userId, tournamentId);
    }

    @Override
    public Integer unsubscribeToTournament(Long userId, Long tournamentId) {
        return userTournamentRepository.deleteUserTournamentByUserIdAndTournamentId(userId, tournamentId);
    }


}
