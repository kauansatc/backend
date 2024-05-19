package com.a09.a09.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.a09.a09.dtos.AlunoDto;
import com.a09.a09.models.Aluno;

@Service
public class AlunoMapper {
    public AlunoDto toDto(Aluno aluno) {
        return new AlunoDto(aluno.getNome(), aluno.getIdade(), aluno.getSala());
    }

    public List<AlunoDto> toDto(List<Aluno> alunos) {
        List<AlunoDto> dtos = new ArrayList<>();
        for (Aluno aluno : alunos) {
            dtos.add(toDto(aluno));
        }
        return dtos;
    }
}
