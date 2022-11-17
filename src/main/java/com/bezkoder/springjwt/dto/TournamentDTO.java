package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.Category;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TournamentDTO {

    private Long id;

    private String name;

    private String description;

    private Timestamp start_date;

    private Timestamp end_date;

    private String prize_fund;

    private Category category_id;
}
