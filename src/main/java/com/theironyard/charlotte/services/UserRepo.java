package com.theironyard.charlotte.services;

import com.theironyard.charlotte.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jake on 5/10/17.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
        User findFirstByName(String userName); //return the first user that has this name
}
