package com.bezkoder.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(	name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 30)
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @NotEmpty
    @Column(name = "start_date")
    private Timestamp start_date;

    @NotEmpty
    @Column(name = "end_date")
    private Timestamp end_date;

    @NotEmpty
    @Column(name = "prize_fund")
    private String prize_fund;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    private Category category_id;

    public Tournament() {}

    public Tournament(String name, String description, Timestamp start_date, Timestamp end_date, String prize_fund, Category category_id) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.prize_fund = prize_fund;
        this.category_id = category_id;
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

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public String getPrize_fund() {
        return prize_fund;
    }

    public void setPrize_fund(String prize_fund) {
        this.prize_fund = prize_fund;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }
}
