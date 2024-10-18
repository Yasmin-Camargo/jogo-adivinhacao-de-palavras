package com.projetotabia.adivinhacao_de_palavras.controllers;

import com.projetotabia.adivinhacao_de_palavras.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/start")
    public ResponseEntity<String> startGame(){
        return ResponseEntity.status(HttpStatus.OK).body(gameService.startGame());
    }

    @GetMapping("/check/{word}")
    public ResponseEntity<String> checkWord(@PathVariable(value="word") String word) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.checkWord(word));
    }


}
