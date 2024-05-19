package com.a09.a09.repos;

import java.util.UUID;

import com.a09.a09.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepo extends JpaRepository<Aluno, UUID> {
    Aluno findByNome(String nome);
}