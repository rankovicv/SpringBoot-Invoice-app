package com.code.example.persistence.repositories;

import com.code.example.persistence.entities.UserCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by veljko on 17.9.18.
 */
public interface UserCompanyRepository extends CrudRepository<UserCompany, Long> {

    UserCompany findByUser_Id(@Param("id") Long id);
}
