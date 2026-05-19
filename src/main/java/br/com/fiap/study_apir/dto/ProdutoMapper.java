package br.com.fiap.study_apir.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.fiap.study_apir.model.Produto;

@Component
public class ProdutoMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Produto toModel(ProdutoCreateRequest dto) {
        return modelMapper.map(dto, Produto.class);
    }

    public ProdutoResponse toDto(Produto entity) {
        return modelMapper.map(entity, ProdutoResponse.class);
    }

    public Produto toModel(Long id, ProdutoUpdateRequest dto) {
        Produto produto = modelMapper.map(dto, Produto.class);
        produto.setId(id);
        return produto;
    }
}
