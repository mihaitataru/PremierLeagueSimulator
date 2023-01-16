package com.premier.league.app.Services;

import com.premier.league.app.Repositories.ClubRepository;
import com.premier.league.app.Repositories.PlayerRepository;
import com.premier.league.app.entities.Club;
import com.premier.league.app.entities.Player;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private ClubRepository clubRepository;

    @Override
    public Player addPlayer(String name, int age, String description, String clubName, String link, int att, int def) {
        Player player = new Player();
        player.setName(name);
        player.setAge(age);
        player.setDescription(description);
        player.setClub(clubRepository.findByName(clubName));
        player.setLink(link);
        player.setATT(att);
        player.setDEF(def);

        playerRepository.save(player);

        return player;
    }

    @Override
    public Player addPlayer(String name, int age, String clubName, String link) {
        Player player = new Player();
        Club club = clubRepository.findByName(clubName);
        player.setName(name);
        player.setAge(age);
        player.setClub(clubRepository.findByName(clubName));
        player.setLink(link);

        playerRepository.save(player);
        club.getPlayers().add(player);
        clubRepository.save(club);

        return player;
    }

    @Override
    @Transactional
    public void deletePlayer(String name) {
        Player player = playerRepository.findByName(name);
        Club club = player.getClub();

        club.getPlayers().remove(player);
    }

    @Override
    @Transactional
    public Map<String, String> getPlayer(String name) {
        Player player = playerRepository.findByName(name);
        if(player == null)
            return null;

        Map<String, String> response = new HashMap<>();
        response.put("name", player.getName());
        response.put("age",Integer.toString(player.getAge()));
        response.put("link", player.getLink());

        return response;
    }
}
