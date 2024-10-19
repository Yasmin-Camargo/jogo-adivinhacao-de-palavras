package com.projetotabia.adivinhacao_de_palavras.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Data Transfer Object for words")
public record WordsRecordDto(
        @Schema(description = "The word to be saved") @NotBlank String word,
        @Schema(description = "Description of the word") @NotBlank String description,
        @Schema(description = "Synonymous of the word") @NotBlank String synonymous,
        @Schema(description = "Level of the word") @NotBlank String level) {
}