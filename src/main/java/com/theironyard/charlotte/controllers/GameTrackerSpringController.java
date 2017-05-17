package com.theironyard.charlotte.controllers;

import com.theironyard.charlotte.entities.Game;
import com.theironyard.charlotte.services.GameRepo;
import com.theironyard.charlotte.entities.User;
import com.theironyard.charlotte.services.UserRepo;
import com.theironyard.charlotte.ultilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GameTrackerSpringController {
    @Autowired //will cause spring to populate this field when it creates this controller.
            GameRepo games; //spring injects this for us ("dependency injection")

    @Autowired
    UserRepo users;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) { //if no user, add user to class then save it to SQL Server
            user = new User(userName, PasswordStorage.createHash(password));
            users.save(user);
        }
//        else if (!PasswordStorage.verifyPassword(password, user.getPasswordHash())) { //if input password doesnt match password saved on SQL, throw err
//            throw new Exception("Incorrect password");
//        }
        session.setAttribute("userName", userName); //store username in session
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate(); //logout invalidates session.
        return "redirect:/";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String genre, Integer releaseYear, String search) {
        List<Game> gameList;
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        if (user != null) {
            model.addAttribute("user", user); //lets us use mustache to fill out user
        }

        if (genre != null) {
            gameList = games.findByGenre(genre); //sort by genre
        } else if (releaseYear != null) {
            gameList = games.findByReleaseYear(releaseYear); //sort by year
        }else if(search != null) {
            gameList = games.findByNameStartsWith(search);
        } else {
            gameList = (List)games.findAll(); //if url doesn't specify, return all of them.
        }
        model.addAttribute("games", gameList);
        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(HttpSession session, String gameName, String gamePlatform, String gameGenre, int gameYear) {
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName); //gets user from session, needed to create game.
        Game game = new Game(gameName, gamePlatform, gameGenre, gameYear, user);
        games.save(game);
        return "redirect:/";
    }

    @PostConstruct
    public void init() throws PasswordStorage.CannotPerformOperationException {
        if (users.count() == 0) {
            User user = new User();
            user.setName("Zach");
            user.setPassword(PasswordStorage.createHash("hunter2"));
            users.save(user);
        }
    }

}