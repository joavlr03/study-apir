package br.com.fiap.study_apir.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.study_apir.model.Produto;

@Service
public class RepositoryProdutoMockup {
    private List<Produto> produtos = new ArrayList<>();
    private long ID = 1L;
    
    public RepositoryProdutoMockup() {        
        produtos.add(new Produto(ID++, "Maça", BigDecimal.valueOf(10.50)));  
        produtos.add(new Produto(ID++, "Uva", BigDecimal.valueOf(15.23)));            
    }

    public List<Produto> findAll() {
        return produtos;
    }

    public Optional<Produto> findById(Long id){
        return produtos.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
    }

    public boolean deleteById(Long id) {
        return produtos.removeIf(p -> p.getId().equals(id));
    }

    public Produto create(Produto produto) {        
        // atribuir o id novo ao produto a ser cadastrado
        produto.setId(ID++);        
        // salvar no BD
        produtos.add(produto);
        // retornar o produto novo        
        return produto;
    }

    public boolean update(Long id, Produto produto) {
        Optional<Produto> optProduto = this.findById(id);
        
        if (optProduto.isPresent()) {
            Produto produtoAtual = optProduto.get();
            produtoAtual.setNome(produto.getNome());
            produtoAtual.setValor(produto.getValor());
            return true;
        }

        return false;
    }

}
