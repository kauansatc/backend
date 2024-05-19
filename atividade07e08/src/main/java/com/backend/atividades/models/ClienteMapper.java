package com.backend.atividades.models;

import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {
    public ClienteDto toDto(Cliente cliente) {
        return new ClienteDto(cliente.getNome(), cliente.getSaldo());
    }
}
