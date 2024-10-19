package com.projetotabia.adivinhacao_de_palavras.controllers;

import com.projetotabia.adivinhacao_de_palavras.dtos.WordsRecordDto;
import com.projetotabia.adivinhacao_de_palavras.models.WordsModel;
import com.projetotabia.adivinhacao_de_palavras.service.WordsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/words")
public class WordsController {

    @Autowired
    private WordsService wordsService;

    @Operation(summary = "Save a new word", description = "This endpoint allows you to save a new word with its definition, synonymous, and level.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Word created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<WordsModel> saveWord(@RequestBody @Valid WordsRecordDto wordsRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(wordsService.saveWord(wordsRecordDto));
    }

    @Operation(summary = "Get all words", description = "This endpoint retrieves a list of all words in the database.")
    @GetMapping
    public ResponseEntity<List<WordsModel>> getAllWords() {
        return ResponseEntity.status(HttpStatus.OK).body(wordsService.getAllWords());
    }

    @Operation(summary = "Get a word by ID", description = "This endpoint retrieves a word by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Word found"),
            @ApiResponse(responseCode = "404", description = "Word not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneWord(
            @Parameter(description = "ID of the word to be retrieved", required = true) @PathVariable(value = "id") Long id) {

        Optional<WordsModel> wordO = wordsService.getOneWord(id);
        if (wordO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(wordO.get());
    }

    @Operation(summary = "Update a word by ID", description = "This endpoint updates the details of a word identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Word updated successfully"),
            @ApiResponse(responseCode = "404", description = "Word not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWord(
            @Parameter(description = "ID of the word to be updated", required = true) @PathVariable(value = "id") Long id,
            @RequestBody @Valid WordsRecordDto wordsRecordDto) {

        Optional<WordsModel> wordO = wordsService.getOneWord(id);
        if (wordO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word not found");
        }
        var wordModel = wordO.get();
        return ResponseEntity.status(HttpStatus.OK).body(wordsService.updateWord(wordModel, wordsRecordDto));
    }

    @Operation(summary = "Delete a word by ID", description = "This endpoint deletes a word identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Word deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Word not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWord(@Parameter(description = "ID of the word to be deleted", required = true) @PathVariable(value = "id") Long id) {
        Optional<WordsModel> wordO = wordsService.getOneWord(id);
        if (wordO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word not found");
        }
        wordsService.deleteWord(wordO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Word deleted successfully");
    }
}
