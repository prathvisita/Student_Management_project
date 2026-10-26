package com.pk.Student_Management_project.Repository;

import com.pk.Student_Management_project.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    boolean existsByUsername(String username);
  //  Users findByEmail(String email);
}
