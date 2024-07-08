package com.ayoungmk.orders_sibs.service;

import java.util.List;

import com.ayoungmk.orders_sibs.exception.UsersNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.UsersDTO;

public interface UsersService {
	
	public List<UsersDTO> findAll();
	public UsersDTO findById(Long id) throws UsersNotFoundException;
	public UsersDTO save(UsersDTO usersDto);
	public UsersDTO updateUsers(Long id, UsersDTO usersDtoDetails) throws UsersNotFoundException;
	public void deleteById(Long id) throws UsersNotFoundException;
}
