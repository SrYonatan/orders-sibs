package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ayoungmk.orders_sibs.model.dto.ItemDTO;
import com.ayoungmk.orders_sibs.model.entity.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItensMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public Item toEntity(ItemDTO dto) {
		Item entity = mapper.map(dto, Item.class);
		return entity;
	}
	
	public ItemDTO toDTO(Item entity) {
		ItemDTO dto = mapper.map(entity, ItemDTO.class);
		return dto;
	}
	
	public List<ItemDTO> toDTO(List<Item> itens){
		return itens.stream()
				.map(item -> toDTO(item))
				.collect(Collectors.toList());
	}
	
}
