package com.backend.atividades.controllers;

import com.backend.atividades.models.*;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    private HashMap<String, Cliente> clientes = new HashMap<>();
    {
        clientes.put("João", new Cliente("João", 300.0, "123"));
        clientes.put("Kauan", new Cliente("Kauan", 4000.0, "marialinda"));
        clientes.put("Dagoberto", new Cliente("Dagoberto", 70.0, "mistoquente72"));
        clientes.put("Batista", new Cliente("Batista", 230.0, "Elefante"));
    };

    @Autowired
    ClienteMapper clienteMapper;

    @GetMapping("/cliente/{nome}")
    public ResponseEntity<?> getCliente(@PathVariable String nome) {
        var cliente = clientes.get(nome);

        if (cliente == null)
            return ResponseEntity.status(404).body("Cliente não encontrado");

        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }
}
