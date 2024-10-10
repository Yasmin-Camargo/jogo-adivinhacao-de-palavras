package com.projetotabia.adivinhacao_de_palavras.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;


// Model class is a model that represents the word entity in the database
@Getter @Setter
@Entity
@Table(name = "TB_WORDS")
public class WordsModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idWord;
    private String word;
    private String description;

}
