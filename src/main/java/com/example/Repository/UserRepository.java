package com.example.Repository;

import com.example.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByuserid(Integer userId);

    //User findByEmailId(String emailId);

}
