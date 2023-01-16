package com.premier.league.app.Repositories;

import com.premier.league.app.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    public void deleteAllByName(String name);

    public void deleteByName(String name);

    public Player findByName(String name);
}
