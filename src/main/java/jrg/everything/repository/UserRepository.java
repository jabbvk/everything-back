package jrg.everything.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jrg.everything.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByMail(String mail);
    
}