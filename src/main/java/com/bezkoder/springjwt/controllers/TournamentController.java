package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.models.Tournament;
import com.bezkoder.springjwt.security.services.TournamentService;
import com.bezkoder.springjwt.util.CheckBindingResult;
import com.bezkoder.springjwt.util.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tournament")
public class TournamentController {

    private final TournamentService tournamentService;
    private final ModelMapper modelMapper;
    private final CheckBindingResult checkBindingResult;

    @Autowired
    public TournamentController(TournamentService tournamentService, ModelMapper modelMapper, CheckBindingResult checkBindingResult) {
        this.tournamentService = tournamentService;
        this.modelMapper = modelMapper;
        this.checkBindingResult = checkBindingResult;
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

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public TournamentDTO save(@RequestBody @Valid TournamentDTO tournamentDTO,
                              BindingResult bindingResult) {
        checkBindingResult.check(bindingResult);
        tournamentService.save(convertToTournament(tournamentDTO));
        return tournamentDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TournamentDTO> delete(@PathVariable("id") int id){
        if (tournamentService.findOne(id) == null){
            throw new NotFoundException();
        }
        tournamentService.delete(id);
        return tournamentService.findAll().stream().map(this::convertToTournamentDTO)
                .collect(Collectors.toList());
    }

    public Tournament convertToTournament(TournamentDTO tournamentDTO) {
        return modelMapper.map(tournamentDTO, Tournament.class);
    }

    public TournamentDTO convertToTournamentDTO(Tournament tournament) {
        return modelMapper.map(tournament, TournamentDTO.class);
    }

}
