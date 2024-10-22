package com.projetotabia.adivinhacao_de_palavras.controllers;

import com.projetotabia.adivinhacao_de_palavras.dtos.WordsRecordDto;
import com.projetotabia.adivinhacao_de_palavras.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.equalTo;

/**
 * Tests for the GameController class.
 * This test suite verifies the game-related functionalities.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private GameService gameService;

    /**
     * Setup method to initialize before each test.
     * This method prepares the game state: adds a word to the game and starts a match.
     */
    @BeforeEach
    void setup() {
        var wordsRecordDto = new WordsRecordDto("computer", "Machine intended for data processing", "laptop", "1");
        webTestClient
                .post()
                .uri("/words")
                .bodyValue(wordsRecordDto)
                .exchange()
                .expectStatus().isCreated();
        gameService.startGame();
    }

    /**
     * Tests successful starting of a new game.
     * Expects to receive a response with description; synonymous.
     */
    @Test
    @DirtiesContext
    void testStartGameSuccess() {
        webTestClient
                .get()
                .uri("/game/start")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.description").isEqualTo("Machine intended for data processing")
                .jsonPath("$.synonymous").isEqualTo("laptop");
    }

    /**
     * Tests successful check of a guessed word.
     * Expects to return a message confirming the guessed word is correct.
     */
    @Test
    @DirtiesContext
    void testCheckWordSuccess() {
        webTestClient
                .get()
                .uri("/game/check/{word}", "computer")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(equalTo("Parabéns! Você acertou!!! A palavra era computer!"));
    }

    /**
     * Tests checking a guessed word that is incorrect.
     * Expect to return a message informing the number of attempts and that the word is incorrect.
     */
    @Test
    @DirtiesContext
    void testCheckWordFail() {
        webTestClient
                .get()
                .uri("/game/check/{word}", "tablet")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(equalTo("Você errou! Tente novamente. Restam 2 tentativas."));
    }

    /**
     * Tests that check when the player has used all attempts
     * Expect to return a message informing that the player lost
     */
    @Test
    @DirtiesContext
    void testLostGame() {
        webTestClient
                .get()
                .uri("/game/check/{word}", "tablet")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(equalTo("Você errou! Tente novamente. Restam 2 tentativas."));

        webTestClient
                .get()
                .uri("/game/check/{word}", "smartphone")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(equalTo("Você errou! Tente novamente. Restam 1 tentativas."));

        webTestClient
                .get()
                .uri("/game/check/{word}", "notebook")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(equalTo("Suas tentativas acabaram. A palavra era: computer"));

    }
}
