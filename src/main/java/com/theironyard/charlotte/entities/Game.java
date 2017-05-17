package com.theironyard.charlotte.entities;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @Id //specifies ID is the primary key of the database. will be dynamicly generated.
    @GeneratedValue
    int id;

    @Column(nullable = false) //will not allow this value to be null(empty).
    String name;

    @Column(nullable = false)
    String platform;

    @Column(nullable = false)
    String genre;

    @Column(nullable = false)
    int releaseYear;

    @ManyToOne //many games can belong to one user
            User user;

    public Game() {
    }

    public Game(String name, String platform, String genre, int releaseYear, User user) {
        this.name = name;
        this.platform = platform;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.user = user;

    }
}