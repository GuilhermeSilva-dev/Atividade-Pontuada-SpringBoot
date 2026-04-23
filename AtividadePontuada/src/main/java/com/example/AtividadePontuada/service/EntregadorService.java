package com.example.AtividadePontuada.service;

import com.example.AtividadePontuada.model.EntregadorModel;
import com.example.AtividadePontuada.model.FuncionarioModel;
import com.example.AtividadePontuada.repository.EntregadorRepository;
import com.example.AtividadePontuada.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregadorService {
    @Autowired
    private EntregadorRepository repository;

    public List<EntregadorModel> listarTodos() {
        return repository.findAll();
    }

    public EntregadorModel salvarEntregador(EntregadorModel entregador) {
        // Verifica se o entregador não esta cadastrado no banco de dados
        // antes de salvar
        if (repository.findByEmail(entregador.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Entregador já cadastrado.");
        }
        return repository.save(entregador);
    }

    public EntregadorModel atualizarEntregador(Long id, EntregadorModel entregador){
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("Entregador não encontrado. ");
        }
        entregador.setId(id);
        return repository.save(entregador);
    }

    public void excluir(Long id){
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("Entregador não encontrado.");
        }
        repository.deleteById(id);
    }
}



