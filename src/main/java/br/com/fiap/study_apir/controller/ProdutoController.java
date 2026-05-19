package br.com.fiap.study_apir.controller;

import java.util.List;

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

import br.com.fiap.study_apir.dto.ProdutoCreateRequest;
import br.com.fiap.study_apir.dto.ProdutoMapper;
import br.com.fiap.study_apir.dto.ProdutoResponse;
import br.com.fiap.study_apir.dto.ProdutoUpdateRequest;
import br.com.fiap.study_apir.model.Produto;
import br.com.fiap.study_apir.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/${api.version}/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service; 

    @Autowired
    private ProdutoMapper produtoMapper;

    @PostMapping
    public ResponseEntity<ProdutoResponse> create(@Valid @RequestBody ProdutoCreateRequest dtoRequest) {                         
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoMapper.toDto(
                service.createOrUpdate(
                    produtoMapper.toModel(dtoRequest)
                )
            ));
    }

    @GetMapping("/{id}")    
    public ResponseEntity<ProdutoResponse> findById(@PathVariable Long id) { 
        return service
                .findById(id)
                .map(produto -> produtoMapper.toDto(produto))                
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());        
    }

    @GetMapping    
    public ResponseEntity<List<ProdutoResponse>> findAll() {        
        return ResponseEntity.ok(
                service.findAll().stream()
                .map(produto -> produtoMapper.toDto(produto))
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> update(@PathVariable Long id, 
                                @RequestBody ProdutoUpdateRequest dtoRequest) {

        if (service.findById(id).isPresent()) {
            Produto produto = produtoMapper.toModel(id, dtoRequest);
            return ResponseEntity.ok(produtoMapper.toDto(
                            service.createOrUpdate(produto)));
        } else {
            return ResponseEntity.notFound().build();
        }     
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) { 
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }         
    }
}
