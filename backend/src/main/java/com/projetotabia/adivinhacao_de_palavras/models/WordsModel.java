package com.projetotabia.adivinhacao_de_palavras.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * Entity representing a word in the database.
 * This class maps to the "TB_WORDS" table and contains the attributes of a word
 */
@Schema(description = "Entity representing a word in the database")
@Getter @Setter
@Entity
@Table(name = "TB_WORDS")
public class WordsModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier of the word.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the word", example = "1")
    private Long idWord;

    /**
     * The word itself.
     */
    @Schema(description = "The word itself", example = "Computer")
    private String word;

    /**
     * Description of the word.
     */
    @Schema(description = "Description of the word", example = "Machine intended for data processing")
    private String description;

    /**
     * Synonymous of the word.
     */
    @Schema(description = "Synonymous of the word", example = "laptop")
    private String synonymous;

    /**
     * Level of the word.
     */
    @Schema(description = "Level of the word", example = "1 - Easy")
    private String level;
}
