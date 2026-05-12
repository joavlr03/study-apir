package br.com.fiap.study_apir.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.fiap.study_apir.model.Produto;

@Service
public class ProdutoMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Produto toModel(ProdutoCreateRequest dto) {
        return modelMapper.map(dto, Produto.class);
    }
}
