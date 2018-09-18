package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by veljko on 9.9.18.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String email);
}

