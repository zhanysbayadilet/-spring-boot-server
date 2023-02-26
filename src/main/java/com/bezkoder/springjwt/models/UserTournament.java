package com.bezkoder.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tournament")
public class UserTournament {
    @Id
    @Column(name = "id")
    Long id;
    @Column(name = "user_id")
    Long userId;
    @Column(name = "tournament_id")
    Long tournamentId;
}
