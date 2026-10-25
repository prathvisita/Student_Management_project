package com.pk.Student_Management_project.Repository;

import com.pk.Student_Management_project.Model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends CrudRepository<Users, Long> {

    Users findByUsername(String username);
}
