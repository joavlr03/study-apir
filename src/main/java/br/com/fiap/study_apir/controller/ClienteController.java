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

import br.com.fiap.study_apir.model.Cliente;
import br.com.fiap.study_apir.repository.ClienteRepository;

@RestController
@RequestMapping("api/${api.version}/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

     @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {         
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
    }

    @GetMapping("/{id}")    
    public ResponseEntity<Cliente> findById(@PathVariable Long id) { 
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());        
    }

    @GetMapping    
    public ResponseEntity<List<Cliente>> findAll() {        
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, 
                                @RequestBody Cliente cliente) {

        Optional<Cliente> optCliente = repository.findById(id);

        if (optCliente.isPresent()) {
            cliente.setId(id);
            Cliente clienteAlterado = repository.save(cliente);
            return ResponseEntity.ok(clienteAlterado);
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
