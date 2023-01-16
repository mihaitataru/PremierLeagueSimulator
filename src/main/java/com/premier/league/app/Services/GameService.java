package com.premier.league.app.Services;

import org.springframework.stereotype.Service;

@Service
public interface GameService {
    public String playGame(String homeTeam, String awayTeam);

    public void playAllGames();
}
