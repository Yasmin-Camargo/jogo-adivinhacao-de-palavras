package com.projetotabia.adivinhacao_de_palavras.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for transferring game-related data when starting a game.
 * This class represents the structure of the data used when initiating a game.
 *
 * @param description A description of the word.
 * @param synonymous  A synonym for the word.
 */
@Schema(description = "Data Transfer Object for starting a game")
public record GameStartDto(
        @Schema(description = "Description of the word") @NotBlank String description,
        @Schema(description = "Synonymous of the word") @NotBlank String synonymous) {
}
