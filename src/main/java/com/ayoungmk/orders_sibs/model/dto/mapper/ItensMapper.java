package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayoungmk.orders_sibs.model.dto.ItensDTO;
import com.ayoungmk.orders_sibs.model.entity.Itens;

@Component
public class ItensMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public Itens toEntity(ItensDTO dto) {
		Itens entity = mapper.map(dto, Itens.class);
		return entity;
	}
	
	public ItensDTO toDTO(Itens entity) {
		ItensDTO dto = mapper.map(entity, ItensDTO.class);
		return dto;
	}
	
	public List<ItensDTO> toDTO(List<Itens> itens){
		return itens.stream()
				.map(item -> toDTO(item))
				.collect(Collectors.toList());
	}
	
}
