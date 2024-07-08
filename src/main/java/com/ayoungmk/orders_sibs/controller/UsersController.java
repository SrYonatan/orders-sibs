package com.ayoungmk.orders_sibs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayoungmk.orders_sibs.exception.UsersNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.UsersDTO;
import com.ayoungmk.orders_sibs.service.impl.UsersServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderSibs/v1/users")
public class UsersController {

	private UsersServiceImpl usersServiceImpl;
	
	public UsersController (@Autowired UsersServiceImpl usersServiceImpl) {
		this.usersServiceImpl = usersServiceImpl;
	}
	
	@GetMapping
	public ResponseEntity<List<UsersDTO>> getAllUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(usersServiceImpl.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsersDTO> getUsersById(@PathVariable Long id) throws UsersNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(usersServiceImpl.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<UsersDTO> createUsers(@RequestBody @Valid UsersDTO userDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(usersServiceImpl.save(userDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsersDTO> updateUsers(@PathVariable Long id, @RequestBody UsersDTO userDtoDetails){
		return ResponseEntity.status(HttpStatus.OK).body(usersServiceImpl.updateUsers(id, userDtoDetails));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUsers(@PathVariable Long id){
			usersServiceImpl.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Removed successfully!");
	}
}
