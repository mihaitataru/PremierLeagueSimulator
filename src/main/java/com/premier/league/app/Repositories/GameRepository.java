package com.premier.league.app.Repositories;

import com.premier.league.app.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    public Game findByAwayTeamid(long id);

    public List<Game> findGamesByHomeTeamid(long id);
}
