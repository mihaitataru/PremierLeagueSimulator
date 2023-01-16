package com.premier.league.app.Repositories;

import com.premier.league.app.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    public Club findByName(String name);
    public Club findClubByStadiumId(Long id);
}
