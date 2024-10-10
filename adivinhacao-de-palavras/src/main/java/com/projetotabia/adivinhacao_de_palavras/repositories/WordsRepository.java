package com.projetotabia.adivinhacao_de_palavras.repositories;

import com.projetotabia.adivinhacao_de_palavras.models.WordsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// Repository interface is responsible for interacting with the database, using the JpaRepository interface
@Repository
public interface WordsRepository extends JpaRepository<WordsModel, UUID> {
}
