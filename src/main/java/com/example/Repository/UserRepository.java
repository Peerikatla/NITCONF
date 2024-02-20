package com.example.Repository;

import com.example.model.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    Optional<User> findByuserid(Integer userId);

    //User findByEmailId(String emailId);

}
