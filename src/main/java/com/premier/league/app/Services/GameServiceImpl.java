package com.premier.league.app.Services;

import com.premier.league.app.Repositories.ClubRepository;
import com.premier.league.app.Repositories.GameRepository;
import com.premier.league.app.Repositories.PlayerRepository;
import com.premier.league.app.entities.Club;
import com.premier.league.app.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public String playGame(String homeTeam, String awayTeam) {
        String response;
        if(isPossibleMatch(homeTeam, awayTeam)){
            response = "Already played";
            return response;
        }
        Club homeClub = clubRepository.findByName(homeTeam);
        Club awayClub = clubRepository.findByName(awayTeam);

        Game game = new Game();
        game.setHomeTeamid(homeClub.getId());
        game.setAwayTeamid(awayClub.getId());

        gameRepository.save(game);

        int homeTeamRating = homeClub.getAttackRating() - awayClub.getDeffenseRating() + 100;
        float homeTeamGoalChance = 0.f;
        if(homeTeamRating < 0){
            homeTeamGoalChance = 1.f;
        }else{
            homeTeamGoalChance = (homeTeamRating * 100f) / homeClub.getAttackRating();
        }

        int awayTeamRating = awayClub.getAttackRating() - homeClub.getDeffenseRating() + 100;
        float awayTeamGoalChance = 0.f;
        if(awayTeamRating < 0){
            awayTeamGoalChance = 1.f;
        }else{
            awayTeamGoalChance = (awayTeamRating * 100f) / awayClub.getAttackRating();
        }

        int homeGoals = 0;
        int awayGoals = 0;

        for(int i = 0; i < 90; i+=10){
            int homeChance = (int) (Math.random() * 100);
            int awayChance = (int) (Math.random() * 100);

            if(homeTeamGoalChance > homeChance - 10)
                homeGoals++;
            if(awayTeamGoalChance > awayChance - 5)
                awayGoals++;
        }

        if(homeGoals > awayGoals){
            homeClub.setGF(homeClub.getGF() + 1);
            homeClub.setPoints(homeClub.getPoints() + 3);
            awayClub.setGA(awayClub.getGA() + 1);
        } else if (homeGoals < awayGoals) {
            homeClub.setGA(homeClub.getGA() + 1);
            awayClub.setGF(awayClub.getGF() + 1);
            awayClub.setPoints(awayClub.getPoints() + 3);
        }else {
            homeClub.setGD(homeClub.getGD() + 1);
            homeClub.setPoints(homeClub.getPoints() + 1);
            awayClub.setGD(awayClub.getGD() + 1);
            awayClub.setPoints(awayClub.getPoints() + 1);
        }

        clubRepository.save(homeClub);
        clubRepository.save(awayClub);

        response = "SUCCESS";
        return response;
    }

    @Override
    public void playAllGames() {
        for(int i = 0; i < 20; i++){
            Club club1 = clubRepository.findClubByStadiumId((long) i);
            for(int j = 0; j < 20; j++) {
                if(i != j) {
                    Club club2 = clubRepository.findClubByStadiumId((long) j);
                    String res = playGame(club1.getName(), club2.getName());
                    res.trim();
                }
            }
        }
    }

    private boolean isPossibleMatch(String team1, String team2) {
        Club club1 = clubRepository.findByName(team1);
        Club club2 = clubRepository.findByName(team2);
        try {
            List<Game> games = gameRepository.findGamesByHomeTeamid(club1.getId());
            if(games == null || games.isEmpty())
                return false;
            boolean played = false;
            for(Game game: games) {
                played = game.getAwayTeamid() == club2.getId();
            }
            return played;
        }catch (Exception e){
            return false;
        }
    }
}
