package com.projetotabia.adivinhacao_de_palavras.service;

import com.projetotabia.adivinhacao_de_palavras.models.WordsModel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
public class GameService {

    @Autowired
    private WordsService wordsService;

    private WordsModel currentWord;

    public String startGame(){
        currentWord = wordsService.getRandomWord();
        return currentWord.getDescription() + ";" + currentWord.getLevel() + ";" + currentWord.getSynonymous();
    }

    public boolean checkWord(String word){
        word = normalizeWord(word);
        if(word.equals(normalizeWord(currentWord.getWord()))){
            return true;
        }
        return false;
    }

    public String getCurrentWord(){
        return currentWord.getWord();
    }

    public String normalizeWord(String input) {
        String normalized = Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replaceAll("ç", "-").replaceAll("", "c");
    }


}
