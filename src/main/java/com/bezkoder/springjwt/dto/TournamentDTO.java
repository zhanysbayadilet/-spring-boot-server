package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.Category;
import lombok.Data;

import java.util.Date;

@Data
public class TournamentDTO {

    private Long id;

    private String name;

    private String description;

    private Date start_date;

    private Date end_date;

    private String prize_fund;

    private String tournament_img;

    private Category category_id;
}
