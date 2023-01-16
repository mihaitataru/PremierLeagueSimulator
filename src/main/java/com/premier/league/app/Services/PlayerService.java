package com.premier.league.app.Services;

import com.premier.league.app.entities.Player;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PlayerService {
    public Player addPlayer(String name, int age, String description, String clubName, String link, int att, int def);

    public Player addPlayer(String name, int age, String clubName, String link);
    @Transactional
    public void deletePlayer(String name);
    @Transactional
    public Map<String, String> getPlayer(String name);
}
