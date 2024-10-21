package com.projetotabia.adivinhacao_de_palavras.service;

import com.projetotabia.adivinhacao_de_palavras.dtos.WordsRecordDto;
import com.projetotabia.adivinhacao_de_palavras.models.WordsModel;
import com.projetotabia.adivinhacao_de_palavras.repositories.WordsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Service class containing all the business logic for managing words.
 * This includes saving, updating, searching, and deleting words,
 * as well as interacting directly with the repository.
 */
@Service
public class WordsService {

    @Autowired
    WordsRepository wordsRepository;

    /**
     * Saves a new word in the repository.
     *
     * @param wordsRecordDto the DTO containing word details to be saved
     * @return the saved WordsModel entity
     */
    public WordsModel saveWord(WordsRecordDto wordsRecordDto) {
        var wordsModel = new WordsModel();
        BeanUtils.copyProperties(wordsRecordDto, wordsModel);
        return wordsRepository.save(wordsModel);
    }

    /**
     * Retrieves all words from the repository.
     *
     * @return a list of WordsModel entities
     */
    public List<WordsModel> getAllWords(){
        return wordsRepository.findAll();
    }

    /**
     * Retrieves a word by its ID.
     *
     * @param id the ID of the word to retrieve
     * @return an Optional containing the found WordsModel entity, or empty if not found
     */
    public Optional<WordsModel> getOneWord(Long id){
        return wordsRepository.findById(id);
    }

    /**
     * Updates the details of an existing word.
     *
     * @param wordModel the existing word model to be updated
     * @param wordsRecordDto the DTO containing updated word details
     * @return the updated WordsModel entity
     */
    public WordsModel updateWord(WordsModel wordModel, WordsRecordDto wordsRecordDto){
        BeanUtils.copyProperties(wordsRecordDto, wordModel);
        return wordsRepository.save(wordModel);
    }

    /**
     * Deletes a word from the repository.
     *
     * @param wordModel the word model to be deleted
     */
    public void deleteWord(WordsModel wordModel){
        wordsRepository.delete(wordModel);
    }

    /**
     * Retrieves a random word from the repository.
     *
     * @return a randomly selected WordsModel entity
     */
    public WordsModel getRandomWord(){
        List<WordsModel> words = wordsRepository.findAll();
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}
