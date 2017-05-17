package com.theironyard.charlotte.services;

import com.theironyard.charlotte.entities.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//a repository is what interacts with your database.
//when extending a crud repo interface, the first parameter in <> is the thing you want to store.
public interface GameRepo extends CrudRepository<Game, Integer> {
    //Hibernate will analyze the format of this method
    //and generate a query based off its name.
    //will generate and run a query simlar to select * from games where genre = ?

    List<Game> findByGenre(String genre);
    List<Game> findByReleaseYear(Integer year); //find where games equals release year equals ?
    List<Game> findByGenreAndReleaseYear(String genre, int releaseYear);
    List<Game> findByReleaseYearIsGreaterThanEqual(int minReleaseYear);

    Game findFirstByGenre(String genre);
    int countByGenre(String genre);
    List<Game> findByGenreOrderByNameAsc(String genre);

    @Query("SELECT g FROM Game g WHERE g.name LIKE ?1%")
    List<Game> findByNameStartsWith(String name);


}
