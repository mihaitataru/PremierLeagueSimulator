package com.premier.league.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private long gameId;

    @JoinColumn(table="Clubs", referencedColumnName = "id")
    private long homeTeamid;

    @JoinColumn(table="Clubs", referencedColumnName = "id")
    private long awayTeamid;

    public long getHomeTeamid() {
        return homeTeamid;
    }

    public void setHomeTeamid(long homeTeamid) {
        this.homeTeamid = homeTeamid;
    }

    public long getAwayTeamid() {
        return awayTeamid;
    }

    public void setAwayTeamid(long awayTeamid) {
        this.awayTeamid = awayTeamid;
    }
}
