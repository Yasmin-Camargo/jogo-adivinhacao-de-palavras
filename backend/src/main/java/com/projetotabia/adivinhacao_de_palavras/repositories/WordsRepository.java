package com.projetotabia.adivinhacao_de_palavras.repositories;

import com.projetotabia.adivinhacao_de_palavras.models.WordsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface responsible for interacting with the database for Word entities.
 * It extends the JpaRepository interface, providing built-in methods for CRUD operations.
 */
@Repository
public interface WordsRepository extends JpaRepository<WordsModel, Long> {
}
