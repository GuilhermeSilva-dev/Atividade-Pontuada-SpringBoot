package com.example.AtividadePontuada.service;

import com.example.AtividadePontuada.model.ClienteModel;
import com.example.AtividadePontuada.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<ClienteModel> listarTodos() {
        return repository.findAll();
    }

    public ClienteModel salvarCliente(ClienteModel cliente) {
        // Verifica se o cliente não esta cadastrado no banco de dados
        // antes de salvar
        if (repository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Cliente já cadastrado.");
        }
        return repository.save(cliente);
    }

    public ClienteModel atualizarCliente(Long id, ClienteModel cliente){
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        cliente.setId(id);
        return repository.save(cliente);
    }

    public void excluir(Long id){
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        repository.deleteById(id);
    }
}
