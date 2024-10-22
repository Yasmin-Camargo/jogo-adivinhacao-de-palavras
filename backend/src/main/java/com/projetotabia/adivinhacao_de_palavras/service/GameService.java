package com.projetotabia.adivinhacao_de_palavras.service;

import com.projetotabia.adivinhacao_de_palavras.models.WordsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import com.projetotabia.adivinhacao_de_palavras.dtos.GameStartDto;

/**
 * Service class responsible for managing the game logic.
 * This includes starting the game, checking guessed words, and providing hints.
 */
@Service
public class GameService {

    @Autowired
    private WordsService wordsService;

    private WordsModel currentWord;
    private int numberAttempts;

    /**
     * Starts a new game by selecting a random word and resetting the number of attempts.
     *
     * @return a Dto containing the description and synonymous of the current word
     */
    public GameStartDto startGame(){
        currentWord = wordsService.getRandomWord();
        GameStartDto gameStartDto = new GameStartDto(currentWord.getDescription(), currentWord.getSynonymous());
        numberAttempts = 3;
        return gameStartDto;
    }

    /**
     * Checks if the guessed word is correct and provides feedback.
     *
     * @param word the guessed word by the player.
     *             If the word is "showsynonymous", the synonymous is revealed.
     * @return a message indicating whether the guess was correct, incorrect, or if attempts are exhausted
     */
    public String checkWord(String word){
        if (currentWord == null || currentWord.getWord() == null || currentWord.getWord().isEmpty()) {
            return "O jogo não foi iniciado ainda.";
        }

        word = normalizeWord(word);
        boolean victory = word.equals(normalizeWord(currentWord.getWord()));
        numberAttempts -= 1;

        if (victory && numberAttempts >= 0) {
            return "Parabéns! Você acertou!!! A palavra era " + currentWord.getWord() + "!";
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

    /**
     * Retrieves the current word being guessed.
     *
     * @return the current word
     */
    public String getCurrentWord(){
        return currentWord.getWord();
    }

    /**
     * Normalizes a given word by converting it to lowercase, removing diacritical marks,
     * replacing "ç" with "c", and removing hyphens.
     *
     * @param input the word to be normalized
     * @return the normalized word
     */
    public String normalizeWord(String input) {
        String normalized = Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replaceAll("ç", "c").replaceAll("-", "");
    }
}
