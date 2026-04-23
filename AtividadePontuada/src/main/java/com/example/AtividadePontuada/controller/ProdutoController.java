package com.example.AtividadePontuada.controller;

import com.example.AtividadePontuada.model.ClienteModel;
import com.example.AtividadePontuada.model.ProdutoModel;
import com.example.AtividadePontuada.service.ClienteService;
import com.example.AtividadePontuada.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// @RestController: Define que a classe vai lidar com requisições HTTP, enviando e recebendo JSON.
@RestController
// @RequestMapping: O "endereço" base para tudo que envolva produtos.
@RequestMapping("/produtos")
public class ProdutoController {

    // Faz a ponte com a camada de serviço, onde a lógica de negócio (cálculos, validações) acontece.
    @Autowired
    private ProdutoService service;

    // GET: Simplesmente chama o service para buscar tudo o que está no banco de dados.
    @GetMapping
    public List<ProdutoModel> listarProdutos() {
        return service.listarTodos();
    }

    // POST: Transforma o JSON do Insomnia em um objeto 'produto'.
    // Retorna 201 (Created), que é o status HTTP correto para quando algo novo nasce no banco.
    @PostMapping
    public ResponseEntity<Map<String, Object>> salvarProduto(@RequestBody ProdutoModel produto) {
        service.salvarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "Produto cadastrado com sucesso"));
    }

    // PUT: Recebe o ID pela URL e o objeto com os novos dados pelo Body.
    // O @PathVariable garante que você está editando o produto de ID "X".
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoModel produto) {
        service.atualizarProduto(id,produto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Produto atualizado com sucesso."));
    }

    // DELETE: O comando de remoção. O status 200 (OK) confirma que o item sumiu da prateleira digital.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluirProduto(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Produto excluído com sucesso."));
    }
}