package com.ayoungmk.orders_sibs.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.exception.UsersNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.UsersDTO;
import com.ayoungmk.orders_sibs.model.dto.mapper.UsersMapper;
import com.ayoungmk.orders_sibs.model.entity.Users;
import com.ayoungmk.orders_sibs.repository.UsersRepository;
import com.ayoungmk.orders_sibs.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private UsersMapper usersMapper;
	
	public List<UsersDTO> findAll(){
		List<Users> users = usersRepository.findAll();
		List<UsersDTO> usersDto = usersMapper.toDTO(users);
		return usersDto;
	}
	
	public UsersDTO findById(Long id) throws UsersNotFoundException{
		Optional<Users> user = usersRepository.findById(id);
		if(user.isPresent()) {
			UsersDTO userDto = usersMapper.toDTO(user.get());
			return userDto;
		}else {
			throw new ItensNotFoundException("User with id " + id + " not found!");
		}
	}
	
	public UsersDTO save(UsersDTO userDto) {
		Users user = usersMapper.toEntity(userDto);
		Users createdUser = usersRepository.save(user);
		UsersDTO userResponseDto = usersMapper.toDTO(createdUser);
		return userResponseDto;
	}
	
	public void deleteById(Long id) throws UsersNotFoundException{
		Optional<Users> userOpt = usersRepository.findById(id);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			usersRepository.delete(user);
		}else {
			throw new ItensNotFoundException("User with id " + id + " not found!");
		}
	}

	public UsersDTO updateUsers(Long id, UsersDTO userDtoDetails) throws UsersNotFoundException{
		Optional<Users> userOpt = usersRepository.findById(id);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			user.setName(userDtoDetails.getName());
			user.setEmail(userDtoDetails.getEmail());
			usersRepository.save(user);
			UsersDTO userDto = usersMapper.toDTO(user);
			return userDto;
		}else {
			throw new ItensNotFoundException("User with id " + id + " not found!");
		}
	}
}