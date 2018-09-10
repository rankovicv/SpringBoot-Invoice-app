package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by veljko on 9.9.18.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}