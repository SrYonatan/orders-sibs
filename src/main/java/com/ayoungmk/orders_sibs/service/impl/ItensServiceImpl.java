package com.ayoungmk.orders_sibs.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.ItensDTO;
import com.ayoungmk.orders_sibs.model.dto.mapper.ItensMapper;
import com.ayoungmk.orders_sibs.model.entity.Itens;
import com.ayoungmk.orders_sibs.repository.ItensRepository;
import com.ayoungmk.orders_sibs.service.ItensService;

@Service
public class ItensServiceImpl implements ItensService {
	
	@Autowired
	private ItensRepository itensRepository;
	@Autowired
	private ItensMapper itensMapper;
	
	public List<ItensDTO> findAll(){
		List<Itens> itens = itensRepository.findAll();
		List<ItensDTO> itensDto = itensMapper.toDTO(itens);
		return itensDto;
	}
	
	public ItensDTO findById(Long id) throws ItensNotFoundException{
		Optional<Itens> item = itensRepository.findById(id);
		if(item.isPresent()) {
			ItensDTO itemDto = itensMapper.toDTO(item.get());
			return itemDto;
		}else {
			throw new ItensNotFoundException("Item with id " + id + " not found!");
		}
	}
	
	public ItensDTO save(ItensDTO itemDto) {
		Itens item = itensMapper.toEntity(itemDto);
		Itens createdItem = itensRepository.save(item);
		ItensDTO itemResponseDto = itensMapper.toDTO(createdItem);
		return itemResponseDto;
	}
	
	public void deleteById(Long id) throws ItensNotFoundException{
		Optional<Itens> itemOpt = itensRepository.findById(id);
		if(itemOpt.isPresent()) {
			Itens item = itemOpt.get();
			itensRepository.delete(item);
		}else {
			throw new ItensNotFoundException("Item with id " + id + " not found!");
		}
	}

	public ItensDTO updateItens(Long id, ItensDTO itemDtoDetails) throws ItensNotFoundException{
		Optional<Itens> itemOpt = itensRepository.findById(id);
		if(itemOpt.isPresent()) {
			Itens item = itemOpt.get();
			item.setName(itemDtoDetails.getName());
			itensRepository.save(item);
			ItensDTO itemDto = itensMapper.toDTO(item);
			return itemDto;
		}else {
			throw new ItensNotFoundException("Item with id " + id + " not found!");
		}
	}
}