package com.projetotabia.adivinhacao_de_palavras.dtos;

import jakarta.validation.constraints.NotBlank;

// RecordDto class Transfers and validates data
public record WordsRecordDto(@NotBlank String word, @NotBlank String description) {
}
