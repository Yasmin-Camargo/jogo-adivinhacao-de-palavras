package com.projetotabia.adivinhacao_de_palavras.controllers;

import com.projetotabia.adivinhacao_de_palavras.dtos.WordsRecordDto;
import com.projetotabia.adivinhacao_de_palavras.models.WordsModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Tests for the WordsController class.
 * This test suite verifies the CRUD operations of the words API.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WordsControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    /**
     * Setup method to initialize before each test.
     * This method creates a default word to ensure there is at least one word in the database
     * before executing the tests.
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
    }

    /**
     * Tests successful creation of a new word.
     * Expects the word "phone" to be created with the provided attributes and returns a status of 201 Created.
     */
    @DirtiesContext
    @Test
    void testCreateWordSuccess() {
        var wordsRecordDto = new WordsRecordDto("phone", "Device for communication", "mobile", "1");
        webTestClient
                .post()
                .uri("/words")
                .bodyValue(wordsRecordDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.word").isEqualTo("phone")
                .jsonPath("$.description").isEqualTo("Device for communication")
                .jsonPath("$.synonymous").isEqualTo("mobile")
                .jsonPath("$.level").isEqualTo("1");
    }

    /**
     * Tests failed creation of a word due to invalid data.
     * Expects the status to return 400 Bad Request.
     */
    @DirtiesContext
    @Test
    void testCreateWordFail() {
        var invalidWordsRecordDto = new WordsRecordDto("", "", "", "1");
        webTestClient
                .post()
                .uri("/words")
                .bodyValue(invalidWordsRecordDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    /**
     * Tests retrieval of all words.
     * Expects the status to return 200 OK and that the number of words is 1.
     */
    @Test
    void testGetAllWords() {
        webTestClient
                .get()
                .uri("/words")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(WordsModel.class)
                .hasSize(1);
    }

    /**
     * Tests retrieval of a specific word successfully.
     * Expects the status to return 200 OK and the word "computer".
     */
    @Test
    void testGetOneWordSuccess() {
        webTestClient
                .get()
                .uri("/words/{id}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.word").isEqualTo("computer");
    }

    /**
     * Tests retrieval of a word that does not exist.
     * Expects the status to return 404 Not Found.
     */
    @Test
    void testGetOneWordNotFound() {
        long invalidWordId = 999;
        webTestClient
                .get()
                .uri("/words/{id}", invalidWordId)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class).isEqualTo("Word not found");
    }

    /**
     * Tests successful update of a word.
     * Expects the word "tablet" to be updated and the status to return 200 OK.
     */
    @DirtiesContext
    @Test
    void testUpdateWordSuccess() {
        long wordId = 1;
        var updatedWord = new WordsRecordDto("tablet", "Device for data processing", "ipad", "2");

        webTestClient
                .put()
                .uri("/words/{id}", wordId)
                .bodyValue(updatedWord)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.word").isEqualTo("tablet")
                .jsonPath("$.level").isEqualTo("2");
    }

    /**
     * Tests updating a word that does not exist.
     * Expects the status to return 404 Not Found.
     */
    @DirtiesContext
    @Test
    void testUpdateWordNotFound() {
        long invalidWordId = 999;
        var updatedWord = new WordsRecordDto("tablet", "Device for data processing", "ipad", "2");

        webTestClient
                .put()
                .uri("/words/{id}", invalidWordId)
                .bodyValue(updatedWord)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class).isEqualTo("Word not found");
    }

    /**
     * Tests successful deletion of a word.
     * Expects the word with ID 1 to be deleted and the status to return 200 OK.
     */
    @DirtiesContext
    @Test
    void testDeleteWordSuccess() {
        long wordId = 1;
        webTestClient
                .delete()
                .uri("/words/{id}", wordId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Word deleted successfully");
    }

    /**
     * Tests deletion of a word that does not exist.
     * Expects the status to return 404 Not Found.
     */
    @DirtiesContext
    @Test
    void testDeleteWordNotFound() {
        long invalidWordId = 999;
        webTestClient
                .delete()
                .uri("/words/{id}", invalidWordId)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class).isEqualTo("Word not found");
    }
}
