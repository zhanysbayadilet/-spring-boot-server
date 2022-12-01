package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TournamentRepository extends JpaRepository<Tournament,  Long> {

    @Query(nativeQuery = true, value = "select count(id) from tournament")
    Integer getCountTournaments();
}
