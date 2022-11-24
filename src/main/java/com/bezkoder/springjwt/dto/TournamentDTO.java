package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.Tournament;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.util.ConvertUtils;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TournamentDTO {

    private int id;
    private String name;
    private String description;
    private Date start_date;
    private Date end_date;
    private int prize_fund;
    private String tournament_img;
    private CategoryDTO category;
    private List<User> users;

    public TournamentDTO toDto(Tournament tournament) {
        return TournamentDTO.builder()
                .id(tournament.getId())
                .name(tournament.getName())
                .description(tournament.getDescription())
                .start_date(tournament.getStart_date())
                .end_date(tournament.getEnd_date())
                .prize_fund(tournament.getPrize_fund())
                .tournament_img(tournament.getTournament_img())
                .category(tournament.getCategory() != null ? ConvertUtils.convertToCategoryDTO(tournament.getCategory()) : null)
                .users(tournament.getUsers())
                .build();
    }
}
