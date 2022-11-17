package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament,  Integer> {
}
