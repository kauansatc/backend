package com.backend.atividades.controllers;

import com.backend.atividades.models.*;

import jakarta.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransacaoController {
    @PostMapping("/transacao")
    public ResponseEntity<?> transacao(@RequestBody @Valid TransacaoDto transacao) {
        return ResponseEntity.status(Response.SC_OK).body(transacao);
    }
}
