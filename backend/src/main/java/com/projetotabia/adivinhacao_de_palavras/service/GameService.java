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
    private int numberAttempts;

    public String startGame(){
        currentWord = wordsService.getRandomWord();
        numberAttempts = 3;
        return currentWord.getDescription() + ";" + currentWord.getLevel() + ";" + currentWord.getSynonymous();
    }

    public String checkWord(String word){
        word = normalizeWord(word);
        boolean victory = word.equals(normalizeWord(currentWord.getWord()));
        numberAttempts -= 1;

        if (victory && numberAttempts >= 0) {
            return "Parabéns! Você acertou!";
        }
        else if (numberAttempts == 0 || numberAttempts < 0){
            return "Suas tentativas acabaram. A palavra era: " + currentWord.getWord();
        }
        else if (word.equals("showsynonymous")){
            return "Dica revelada. Restam " + numberAttempts + " tentativas.";
        }
        else {
            return "Você errou! Tente novamente. Restam " + numberAttempts + " tentativas.";
        }
    }

    public String getCurrentWord(){
        return currentWord.getWord();
    }

    public String normalizeWord(String input) {
        String normalized = Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replaceAll("ç", "c").replaceAll("-", "");
    }


}
