package com.ayoungmk.orders_sibs.service.impl;

import java.util.List;
import java.util.Optional;

import com.ayoungmk.orders_sibs.model.dto.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.exception.UsersNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.UserDTO;
import com.ayoungmk.orders_sibs.model.entity.User;
import com.ayoungmk.orders_sibs.repository.UsersRepository;
import com.ayoungmk.orders_sibs.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private UserMapper usersMapper;
	
	public List<UserDTO> findAll(){
		List<User> users = usersRepository.findAll();
		List<UserDTO> UserDTO = usersMapper.toDTO(users);
		return UserDTO;
	}
	
	public UserDTO findById(Long id) throws UsersNotFoundException{
		Optional<User> user = usersRepository.findById(id);
		if(user.isPresent()) {
			UserDTO userDto = usersMapper.toDTO(user.get());
			return userDto;
		}else {
			throw new ItensNotFoundException("User with id " + id + " not found!");
		}
	}
	
	public UserDTO save(UserDTO userDto) {
		User user = usersMapper.toEntity(userDto);
		User createdUser = usersRepository.save(user);
		UserDTO userResponseDto = usersMapper.toDTO(createdUser);
		return userResponseDto;
	}
	
	public void deleteById(Long id) throws UsersNotFoundException{
		Optional<User> userOpt = usersRepository.findById(id);
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			usersRepository.delete(user);
		}else {
			throw new ItensNotFoundException("User with id " + id + " not found!");
		}
	}

	public UserDTO updateUsers(Long id, UserDTO userDtoDetails) throws UsersNotFoundException{
		Optional<User> userOpt = usersRepository.findById(id);
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			user.setName(userDtoDetails.getName());
			user.setEmail(userDtoDetails.getEmail());
			usersRepository.save(user);
			UserDTO userDto = usersMapper.toDTO(user);
			return userDto;
		}else {
			throw new ItensNotFoundException("User with id " + id + " not found!");
		}
	}
}