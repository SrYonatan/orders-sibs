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

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.ItensDTO;
import com.ayoungmk.orders_sibs.service.impl.ItensServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderSibs/v1/itens")
public class ItensController {
	
	private ItensServiceImpl itensServiceImpl;
	
	public ItensController (@Autowired ItensServiceImpl itensServiceImpl) {
		this.itensServiceImpl = itensServiceImpl;
	}
	
	@GetMapping
	public ResponseEntity<List<ItensDTO>> getAllItens(){
		return ResponseEntity.status(HttpStatus.OK).body(itensServiceImpl.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItensDTO> getItensById(@PathVariable Long id) throws ItensNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(itensServiceImpl.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<ItensDTO> createItens(@RequestBody @Valid ItensDTO itemDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(itensServiceImpl.save(itemDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ItensDTO> updateItens(@PathVariable Long id, @RequestBody ItensDTO itemDtoDetails){
		return ResponseEntity.status(HttpStatus.OK).body(itensServiceImpl.updateItens(id, itemDtoDetails));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItens(@PathVariable Long id){
		itensServiceImpl.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Removed successfully!");
	}
}