package jrg.everything.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jrg.everything.models.User;
import jrg.everything.service.UserService;
import jrg.everything.utils.PasswordUtil;

@RestController
@RequestMapping("/user")
public class ControllerUser {

    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public String register(@RequestBody Map<String, Object> payload) {

        // sendMail((String) payload.get("email"));

        User user = new User();
        user.setName((String) payload.get("name"));
        user.setMail((String) payload.get("mail"));
        user.setPassword(PasswordUtil.hashPassword((String) payload.get("password")));
        userService.saveUser(user);

        System.out.println(userService.getUserByMail(user.getMail()));

        return "register";
    }
}
