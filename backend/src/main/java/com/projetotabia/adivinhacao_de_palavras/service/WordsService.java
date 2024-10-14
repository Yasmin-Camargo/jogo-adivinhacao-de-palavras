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

// Service class contains all the business logic, such as saving, updating, searching and deleting words,
// interacting directly with the repository

@Service
public class WordsService {

    @Autowired
    WordsRepository wordsRepository;

    public WordsModel saveWord(WordsRecordDto wordsRecordDto) {
        var wordsModel = new WordsModel();
        BeanUtils.copyProperties(wordsRecordDto, wordsModel);
        return wordsRepository.save(wordsModel);
    }

    public List<WordsModel> getAllWords(){
        return wordsRepository.findAll();
    }

    public Optional<WordsModel> getOneWord(Long id){
        return wordsRepository.findById(id);
    }

    public WordsModel updateWord(WordsModel wordModel, WordsRecordDto wordsRecordDto){
        BeanUtils.copyProperties(wordsRecordDto, wordModel);
        return wordsRepository.save(wordModel);
    }

    public void deleteWord(WordsModel wordModel){
        wordsRepository.delete(wordModel);
    }

    public WordsModel getRandomWord(){
        List<WordsModel> words = wordsRepository.findAll();
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }


}
