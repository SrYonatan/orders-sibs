package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayoungmk.orders_sibs.model.dto.UsersDTO;
import com.ayoungmk.orders_sibs.model.entity.Users;

@Component
public class UsersMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public Users toEntity(UsersDTO dto) {
		Users entity = mapper.map(dto, Users.class);
		return entity;
	}
	
	public UsersDTO toDTO(Users entity) {
		UsersDTO dto = mapper.map(entity, UsersDTO.class);
		return dto;
	}
	
	public List<UsersDTO> toDTO(List<Users> users){
		return users.stream()
				.map(user -> toDTO(user))
				.collect(Collectors.toList());
	}
	
}
