package com.premier.league.app.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Clubs")
public class Club {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @JoinColumn(table = "Stadium", referencedColumnName = "id")
    private long stadiumId;

    private String officialWebSite;

    @OneToMany(targetEntity = Player.class, orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Player> players;

    private int points;

    //losses
    private int GA;

    //wins
    private int GF;

    //draws
    private int GD;

    public long getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStadium() {
        return stadiumId;
    }

    public void setStadium(Long stadium) {
        this.stadiumId = stadium;
    }

    public String getOfficialWebSite() {
        return officialWebSite;
    }

    public void setOfficialWebSite(String officialWebSite) {
        this.officialWebSite = officialWebSite;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGA() {
        return GA;
    }

    public void setGA(int GA) {
        this.GA = GA;
    }

    public int getGF() {
        return GF;
    }

    public void setGF(int GF) {
        this.GF = GF;
    }

    public int getGD() {
        return GD;
    }

    public void setGD(int GD) {
        this.GD = GD;
    }

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                ", stadiumId=" + stadiumId +
                ", officialWebSite='" + officialWebSite + '\'' +
                ", points=" + points +
                ", GA=" + GA +
                ", GF=" + GF +
                ", GD=" + GD +
                '}';
    }

    public int getAttackRating(){
        int res = 0;
        for (Player p:this.getPlayers()) {
            res += p.getATT();
        }
        return res;
    }

    public int getDeffenseRating(){
        int res = 0;
        for (Player p:this.getPlayers()) {
            res += p.getDEF();
        }
        return res - 110;
    }
}
