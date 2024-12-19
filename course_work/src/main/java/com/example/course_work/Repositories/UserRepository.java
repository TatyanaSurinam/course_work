package com.example.course_work.Repositories;



import com.example.course_work.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @Query("select p from User p left join fetch p.roles")
    List<User> findAll();

    @Override
    @Query("select p from User p left join fetch p.roles where p.id = ?1")
    Optional<User> findById(Long id);


    @Query("select p from User p left join fetch p.roles where p.username LIKE %?1%")
    Optional<User> findByUsername(String username);

    @Query("SELECT p FROM User p WHERE CONCAT(p.password, '', p.username, '', p.roles) LIKE %?1%")
    List<User> search(String keyword);


}

