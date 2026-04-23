package com.example.AtividadePontuada.service;

import com.example.AtividadePontuada.model.ProdutoModel;
import com.example.AtividadePontuada.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoModel> listarTodos() {
        return repository.findAll();
    }

    public ProdutoModel salvarProduto(ProdutoModel produto) {
        // Verifica se o produto não esta cadastrado no banco de dados
        // antes de salvar
        if (repository.findByLote(produto.getLote()).isPresent()) {
            throw new IllegalArgumentException("Produto já cadastrado. ");
        }
        return repository.save(produto);
    }

    public ProdutoModel atualizarProduto(Long id, ProdutoModel produto){
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("Produto não encontrado. ");
        }
        produto.setId(id);
        return repository.save(produto);
    }

    public void excluir(Long id){
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("Produto não encontrado. ");
        }
        repository.deleteById(id);
    }
}
