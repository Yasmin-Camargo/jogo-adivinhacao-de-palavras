package com.projetotabia.adivinhacao_de_palavras.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for transferring word-related data between layers.
 * This class represents the structure of the data used when creating or updating words.
 *
 * @param word        The word to be saved.
 * @param description A description of the word.
 * @param synonymous  A synonym for the word.
 * @param level       The difficulty level of the word.
 */
@Schema(description = "Data Transfer Object for words")
public record WordsRecordDto(
        @Schema(description = "The word to be saved") @NotBlank String word,
        @Schema(description = "Description of the word") @NotBlank String description,
        @Schema(description = "Synonymous of the word") @NotBlank String synonymous,
        @Schema(description = "Level of the word") @NotBlank String level) {
}
