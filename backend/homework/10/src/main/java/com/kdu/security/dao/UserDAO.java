package com.kdu.security.dao;

import com.kdu.security.entity.Person;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class UserDAO {
    private final List<Person> users = new ArrayList<>();
    public List<Person> getAll() {
        return users;
    }
    public Optional<Person> getUserByName(String name){
        return users.stream().filter(user -> user.getUserName().equals(name)).findFirst();
    }

    public void addUser(Person user){
        users.add(user);
        log.info("User Added with name --> {}",user.getUserName());
    }


}
