package com.jorder.entregas.common;

import com.jorder.entregas.model.Entrega;
import com.jorder.entregas.model.dto.DestinatarioDto;
import com.jorder.entregas.model.dto.EntregaDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

//    public static EntregaDto mapperEntregaToEntregaDto(Entrega entrega){
//        EntregaDto entregaDto = new EntregaDto();
//        entregaDto.setId(entrega.getId());
//        entregaDto.setNomeCliente(entrega.getCliente().getNome());
//        entregaDto.setStatus(entrega.getStatus());
//        entregaDto.setDestinatarioDto(new DestinatarioDto());
//        entregaDto.getDestinatarioDto().setNome(entrega.getDestinatario().getNome());
//        entregaDto.getDestinatarioDto().setBairro(entrega.getDestinatario().getBairro());
//        entregaDto.getDestinatarioDto().setLogradouro(entrega.getDestinatario().getLogradouro());
//        entregaDto.getDestinatarioDto().setComplemento(entrega.getDestinatario().getComplemento());
//        entregaDto.getDestinatarioDto().setNumero(entrega.getDestinatario().getNumero());
//        entregaDto.setTaxa(entrega.getTaxa());
//        entregaDto.setDataPedido(entrega.getDataPedido());
//        entregaDto.setDataFinalizacao(entrega.getDataFinalizacao());
//        return entregaDto;
//    }

}
