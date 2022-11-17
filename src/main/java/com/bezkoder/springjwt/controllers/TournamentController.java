package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.models.Tournament;
import com.bezkoder.springjwt.security.services.TournamentService;
import com.bezkoder.springjwt.util.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tournament")
public class TournamentController {

    private final TournamentService tournamentService;
    private final ModelMapper modelMapper;

    @Autowired
    public TournamentController(TournamentService tournamentService, ModelMapper modelMapper) {
        this.tournamentService = tournamentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/all")
    public List<TournamentDTO> getTournaments(){
        return tournamentService.findAll().stream().map(this::convertToTournamentDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TournamentDTO getTournament(@PathVariable("id") int id){
        if (tournamentService.findOne(id) == null)
            throw new NotFoundException();
        return convertToTournamentDTO(tournamentService.findOne(id));
    }

    public Tournament convertToTournament(TournamentDTO tournamentDTO) {
        return modelMapper.map(tournamentDTO, Tournament.class);
    }

    public TournamentDTO convertToTournamentDTO(Tournament tournament) {
        return modelMapper.map(tournament, TournamentDTO.class);
    }

}
