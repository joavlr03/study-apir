package br.com.fiap.study_apir.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.study_apir.model.Produto;
import br.com.fiap.study_apir.repository.ProdutoRepository;

@RestController
@RequestMapping("api/${api.version}/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto) {         
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
    }

    @GetMapping("/{id}")    
    public ResponseEntity<Produto> findById(@PathVariable Long id) { 
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());        
    }

    @GetMapping    
    public ResponseEntity<List<Produto>> findAll() {        
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, 
                                @RequestBody Produto produto) {

        Optional<Produto> optProduto = repository.findById(id);

        if (optProduto.isPresent()) {
            produto.setId(id);
            Produto produtoAlterado = repository.save(produto);
            return ResponseEntity.ok(produtoAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }     
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) { 
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
 
    }
}
