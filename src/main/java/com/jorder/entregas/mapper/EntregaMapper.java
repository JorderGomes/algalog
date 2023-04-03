package com.jorder.entregas.mapper;

import com.jorder.entregas.model.Entrega;
import com.jorder.entregas.model.dto.EntregaDto;
import com.jorder.entregas.model.input.EntregaInput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper {

    private ModelMapper modelMapper;

    public EntregaDto toEntregaDto (Entrega entrega){
        return modelMapper.map(entrega, EntregaDto.class);
    }

    public List<EntregaDto> toCollectionEntregaDto(List<Entrega> entregas){
        return entregas.stream()
                .map(this::toEntregaDto)
                .collect(Collectors.toList());
    }

    public Entrega toEntrega(EntregaInput entregaInput){
        return modelMapper.map(entregaInput, Entrega.class);
    }


}
