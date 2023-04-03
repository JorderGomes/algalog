package com.jorder.entregas.mapper;

import com.jorder.entregas.model.Ocorrencia;
import com.jorder.entregas.model.dto.OcorrenciaDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

    private ModelMapper modelMapper;

    public List<OcorrenciaDto> toCollectionOcorrenciaDto(List<Ocorrencia> ocorrencias){
        return ocorrencias.stream()
                .map(this::toOcorrenciaDto)
                .collect(Collectors.toList());
    }

    public OcorrenciaDto toOcorrenciaDto(Ocorrencia ocorrencia){
        return modelMapper.map(ocorrencia, OcorrenciaDto.class);
    }

}
