package com.projetotabia.adivinhacao_de_palavras.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entity representing a word in the database")
@Getter @Setter
@Entity
@Table(name = "TB_WORDS")
public class WordsModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the word", example = "1")
    private Long idWord;

    @Schema(description = "The word itself", example = "Computer")
    private String word;

    @Schema(description = "Description of the word", example = "Machine intended for data processing")
    private String description;

    @Schema(description = "Synonymous of the word", example = "laptop")
    private String synonymous;

    @Schema(description = "Level of the word", example = "1 - Easy")
    private String level;
}
