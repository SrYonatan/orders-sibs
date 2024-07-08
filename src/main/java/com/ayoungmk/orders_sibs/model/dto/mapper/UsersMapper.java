package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayoungmk.orders_sibs.model.dto.UsersDTO;
import com.ayoungmk.orders_sibs.model.entity.User;

@Component
public class UsersMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public User toEntity(UsersDTO dto) {
		User entity = mapper.map(dto, User.class);
		return entity;
	}
	
	public UsersDTO toDTO(User entity) {
		UsersDTO dto = mapper.map(entity, UsersDTO.class);
		return dto;
	}
	
	public List<UsersDTO> toDTO(List<User> users){
		return users.stream()
				.map(user -> toDTO(user))
				.collect(Collectors.toList());
	}
	
}
