package com.example.AtividadePontuada.controller;

import com.example.AtividadePontuada.model.EntregadorModel;
import com.example.AtividadePontuada.model.ProdutoModel;
import com.example.AtividadePontuada.service.EntregadorService;
import com.example.AtividadePontuada.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// @RestController: Avisa pro Spring que aqui é a "central de controle" dos entregadores.
@RestController
// @RequestMapping: Define que o caminho /entregadores é a porta de entrada.
@RequestMapping("/entregadores")
public class EntregadorController {

    // @Autowired: Conecta o controller com o service (quem realmente faz o trabalho pesado).
    @Autowired
    private EntregadorService service;

    // GET: É o famoso "select * from entregadores".
    // O Spring devolve uma lista bonitinha pra quem chamou.
    @GetMapping
    public List<EntregadorModel> listarEntregadores() {
        return service.listarTodos();
    }

    // POST: Recebe o JSON do entregador e passa pro service validar e salvar.
    // O @RequestBody é essencial aqui pro Spring conseguir ler o corpo da requisição.
    @PostMapping
    public ResponseEntity<Map<String, Object>> salvarEntregador(@RequestBody EntregadorModel entregador) {
        service.salvarEntregador(entregador);
        // HttpStatus.CREATED (201): É o status de sucesso perfeito pra novos cadastros.
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "Entregador cadastrado com sucesso"));
    }

    // PUT: Atualiza. O ID vem na URL e o resto dos dados no corpo (JSON).
    // O service vai usar esse ID pra saber QUEM ele tem que mudar.
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarEntregador(@PathVariable Long id, @RequestBody EntregadorModel entregador) {
        service.atualizarEntregador(id,entregador);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Entregador atualizado com sucesso."));
    }

    // DELETE: Pega o ID e manda pro service apagar o registro no banco.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluirEntregador(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Entregador excluído com sucesso."));
    }
}