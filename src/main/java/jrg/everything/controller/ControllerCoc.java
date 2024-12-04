package jrg.everything.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jrg.everything.service.CocService;

@RestController
@RequestMapping("/coc")
public class ControllerCoc {

    @Autowired
    private CocService cocService;

    @GetMapping("/playerInfo/{tag}")
    public String getPlayerInfo(@PathVariable String tag) {
        // Llamar al servicio para obtener la información del jugador
        String playerInfo = cocService.getPlayerInfo(tag);
        
        if (playerInfo == null) {
            // Devolver un mensaje adecuado si hay un error
            return "Error al obtener información del jugador";
        }
        
        return playerInfo; // Devolver la información del jugador
    }

    @GetMapping("/hola")
    public String test() {
        return "Hola Mundo";
    }
}

