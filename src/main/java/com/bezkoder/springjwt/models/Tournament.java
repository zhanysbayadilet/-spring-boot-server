package com.bezkoder.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(	name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_date;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_date;

    @Column(name = "prize_fund")
    private int prize_fund;

    @Column(name = "tournament_img")
    private String tournament_img;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    private Category category_id;

    public Tournament() {}

    public Tournament(String name, String description, Date start_date, Date end_date, int prize_fund, Category category_id, String tournament_img) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.prize_fund = prize_fund;
        this.category_id = category_id;
        this.tournament_img = tournament_img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getPrize_fund() {
        return prize_fund;
    }

    public void setPrize_fund(int prize_fund) {
        this.prize_fund = prize_fund;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public String getTournament_img() {
        return tournament_img;
    }

    public void setTournament_img(String tournament_img) {
        this.tournament_img = tournament_img;
    }
}
