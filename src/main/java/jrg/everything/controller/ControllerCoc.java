package jrg.everything.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jrg.everything.errors.Errors;
import jrg.everything.service.CocService;

@RestController
@RequestMapping("/coc")
public class ControllerCoc {

    @Autowired
    private CocService cocService;

    @PostMapping("/playerInfo/{tag}")
    public ResponseEntity<String> getPlayerInfo(@PathVariable String tag, @RequestBody Map<String, Object> payload) {

        String token = (String) payload.get("token");

        String playerInfo = cocService.getPlayerInfo(tag, token);
        
        if (playerInfo == null) {
            return new ResponseEntity<>(Errors.COC_PLAYER_NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(playerInfo, HttpStatus.OK);
    }
}

