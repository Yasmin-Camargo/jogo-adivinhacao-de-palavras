package com.projetotabia.adivinhacao_de_palavras.controllers;

import com.projetotabia.adivinhacao_de_palavras.dtos.GameStartDto;
import com.projetotabia.adivinhacao_de_palavras.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing game-related functionalities.
 * Provides endpoints for starting a new game and checking guessed words.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * Starts a new game and provides a description, level, and synonymous of the current word.
     *
     * @return ResponseEntity containing the game start message and HTTP status.
     */
    @Operation(summary = "Start a new game", description = "This endpoint starts a new game and provides a description, level, and synonymous of the current word.")
    @GetMapping("/start")
    public ResponseEntity<GameStartDto> startGame() {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.startGame());
    }

    /**
     * Checks if the guessed word is correct and returns the result.
     *
     * @param word The word guessed by the player.
     * @return ResponseEntity containing the check result and HTTP status.
     */
    @Operation(summary = "Check a guessed word", description = "This endpoint checks if the guessed word is correct and returns the result.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Check result returned successfully")
    })
    @GetMapping("/check/{word}")
    public ResponseEntity<String> checkWord(
            @Parameter(description = "The word guessed by the player", required = true) @PathVariable(value = "word") String word) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.checkWord(word));
    }
}
