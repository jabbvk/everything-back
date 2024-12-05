package jrg.everything.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jrg.everything.models.User;
import jrg.everything.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByMail(String mail) {
        return userRepository.findByMail(mail);
    }
}
