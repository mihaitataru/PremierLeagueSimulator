package com.premier.league.app.Services;

import com.premier.league.app.Repositories.ClubRepository;
import com.premier.league.app.Repositories.GameRepository;
import com.premier.league.app.entities.Club;
import com.premier.league.app.entities.Player;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClubServiceImpl implements ClubService{
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Club addClub(String name, Long stadiumId, String officialWebSite) {
        Club club = new Club();
        club.setName(name);
        club.setStadium(stadiumId);
        club.setOfficialWebSite(officialWebSite);

        clubRepository.save(club);
        return club;
    }

    private String parsePlayers(Club club){
        Map<Integer, Map<String,String>> result = new HashMap<>();
        Map<String,String> playerStats;
        int i = 1;
        for (Player player:club.getPlayers()) {
            playerStats = playerService.getPlayer(player.getName());
            result.put(i, playerStats);
            i++;
        }
        return result.toString();
    }

    @Override
    @Transactional
    public Map<String, String> getClub(String name) {
        Club club = clubRepository.findByName(name);
        Map<String, String> response = new HashMap<>();

        response.put("name", club.getName());
        response.put("stadiumId",Long.toString(club.getStadium()));
        response.put("officialWebsite", club.getOfficialWebSite());
        response.put("points", Long.toString(club.getPoints()));
        response.put("GF", Long.toString(club.getGF()));
        response.put("GA", Long.toString(club.getGA()));
        response.put("GD", Long.toString(club.getGD()));

        return response;
    }

    @Override
    public Map<Integer, Map<String, String>> getClubs(){
        Map<Integer, Map<String, String>> result = new HashMap<>();
        for(int i = 0; i < 20; i++){
            Club club = clubRepository.findClubByStadiumId((long) i);
            result.put(i+1, getClub(club.getName()));
        }
        return result;
    }

    @Override
    public void resetGames() {
        for(int i = 0; i < 20; i++){
            Club club = clubRepository.findClubByStadiumId((long) i);
            club.setGA(0);
            club.setGF(0);
            club.setGD(0);
            club.setPoints(0);
            clubRepository.save(club);
        }

        gameRepository.deleteAll();
    }

    @Override
    public Map<Integer, String> getPlayerNames(String clubName) {
        Club club = clubRepository.findByName(clubName);
        Map<Integer, String> result = new HashMap<>();
        int i = 1;
        for(Player player: club.getPlayers()){
            result.put(i, player.getName());
            i++;
        }
        return result;
    }
}
