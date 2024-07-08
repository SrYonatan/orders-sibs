package com.ayoungmk.orders_sibs.service;

import java.util.List;

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.ItensDTO;

public interface ItensService {

	public List<ItensDTO> findAll();
	public ItensDTO findById(Long id) throws ItensNotFoundException;
	public ItensDTO save(ItensDTO itemDto);
	public ItensDTO updateItens(Long id, ItensDTO itemDtoDetails) throws ItensNotFoundException;
	public void deleteById(Long id) throws ItensNotFoundException;
}
