package com.theironyard.charlotte.controllers;

import com.theironyard.charlotte.entities.User;
import com.theironyard.charlotte.services.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Jake on 5/17/17.
 */
@RestController
public class GameTrackerJSONController {
    @Autowired
    UserRepo users;

    @RequestMapping(path = "/getUsers", method = RequestMethod.GET)
    public ArrayList<User> getUsers(User allUsers) {
        return (ArrayList<User>) users.findAll();

    }
    @RequestMapping(path = "/add-user", method = RequestMethod.POST)
    public ArrayList<User> jsonAdd(@RequestBody User newUser) {
        users.save(newUser);
        return (ArrayList<User>) users.findAll();
    }
}
