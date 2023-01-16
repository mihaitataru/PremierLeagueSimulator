package com.premier.league.app.entities;

import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue
    private long id;


    @Column(unique = true)
    private String name;

    @ManyToOne(targetEntity = Club.class, cascade = CascadeType.PERSIST)
    private Club club;

    private int age;

    private String description;

    private String link;

    private int ATT;

    private int DEF;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getATT() {
        return ATT;
    }

    public void setATT(int ATT) {
        this.ATT = ATT;
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }
}
