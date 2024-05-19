package com.a09.a09.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a09.a09.dtos.CreateDto;
import com.a09.a09.dtos.UpdateDto;
import com.a09.a09.models.Aluno;
import com.a09.a09.repos.AlunoRepo;
import com.a09.a09.services.AlunoMapper;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
public class AlunoController {
    @Autowired
    AlunoMapper alunoMapper;

    @Autowired
    AlunoRepo alunoRepo;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        var alunos = alunoRepo.findAll();
        return ResponseEntity.ok(alunoMapper.toDto(alunos));
    }

    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(@PathParam("name") String name) {
        var aluno = alunoRepo.findByNome(name);

        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alunoMapper.toDto(aluno));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateDto req) {
        var aluno = alunoRepo.findByNome(req.nome());

        if (aluno != null) {
            return ResponseEntity.badRequest().body("Aluno já cadastrado");
        }

        var newAluno = alunoRepo.save(new Aluno(req.nome(), req.idade(), req.sala()));

        return ResponseEntity.ok(alunoMapper.toDto(newAluno));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateDto req) {
        var aluno = alunoRepo.findByNome(req.nome());

        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        var idade = req.idade();
        var sala = req.sala();

        if (idade != null)
            aluno.setIdade(idade);
        if (sala != null)
            aluno.setSala(sala);

        alunoRepo.save(aluno);

        return ResponseEntity.ok(alunoMapper.toDto(aluno));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@PathParam("name") String name) {
        var aluno = alunoRepo.findByNome(name);

        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        alunoRepo.delete(aluno);

        return ResponseEntity.ok().build();
    }
}