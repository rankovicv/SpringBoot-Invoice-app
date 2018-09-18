package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.UserCompany;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by veljko on 17.9.18.
 */
public interface UserCompanyRepository extends CrudRepository<UserCompany, Long> {
}
