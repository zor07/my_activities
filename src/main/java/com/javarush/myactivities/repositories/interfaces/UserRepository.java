package com.javarush.myactivities.repositories.interfaces;

import com.javarush.myactivities.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
