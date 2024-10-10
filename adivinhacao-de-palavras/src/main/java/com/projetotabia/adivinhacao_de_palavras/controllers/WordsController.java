package com.projetotabia.adivinhacao_de_palavras.controllers;

import com.projetotabia.adivinhacao_de_palavras.dtos.WordsRecordDto;
import com.projetotabia.adivinhacao_de_palavras.models.WordsModel;
import com.projetotabia.adivinhacao_de_palavras.service.WordsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Controller only handles HTTP requests

@RestController
public class WordsController {

    @Autowired
    WordsService wordsService;

    @PostMapping("/words")
    public ResponseEntity<WordsModel> saveWord(@RequestBody @Valid WordsRecordDto wordsRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(wordsService.saveWord(wordsRecordDto));
    }

    @GetMapping("/words")
    public ResponseEntity<List<WordsModel>> getAllWords() {
        return ResponseEntity.status(HttpStatus.OK).body(wordsService.getAllWords());
    }

    @GetMapping("/words/{id}")
    public ResponseEntity<Object> getOneWord(@PathVariable(value="id") UUID id){
        Optional<WordsModel> wordO = wordsService.getOneWord(id);
        if(wordO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(wordO.get());
    }

    @PutMapping("/words/{id}")
    public ResponseEntity<Object> updateWord(@PathVariable(value="id") UUID id, @RequestBody @Valid WordsRecordDto wordsRecordDto){
        Optional<WordsModel> wordO = wordsService.getOneWord(id);
        if(wordO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word not found");
        }
        var wordModel = wordO.get();

        return ResponseEntity.status(HttpStatus.OK).body(wordsService.updateWord(wordModel, wordsRecordDto));
    }

    @DeleteMapping("/words/{id}")
    public ResponseEntity<Object> deleteWord(@PathVariable(value="id") UUID id){
        Optional<WordsModel> wordO = wordsService.getOneWord(id);
        if(wordO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word not found");
        }
        wordsService.deleteWord(wordO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Word deleted successfully");
    }

}
