package com.premier.league.app.Services;

import com.premier.league.app.entities.Club;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ClubService {
    public Club addClub(String name, Long stadiumId, String officialWebSite);

    @Transactional
    public Map<String, String> getClub(String name);
    public Map<Integer, Map<String, String>> getClubs();

    @Transactional
    public void resetGames();

    public Map<Integer, String> getPlayerNames(String clubName);
}
