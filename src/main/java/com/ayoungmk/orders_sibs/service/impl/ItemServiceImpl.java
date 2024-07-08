package com.ayoungmk.orders_sibs.service.impl;

import java.util.List;
import java.util.Optional;

import com.ayoungmk.orders_sibs.model.dto.ItemDTO;
import com.ayoungmk.orders_sibs.model.dto.mapper.ItemMapper;
import com.ayoungmk.orders_sibs.model.entity.Item;
import com.ayoungmk.orders_sibs.repository.ItemRepository;
import com.ayoungmk.orders_sibs.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemMapper itensMapper;
	
	public List<ItemDTO> findAll(){
		return itensMapper.toDTO(itemRepository.findAll());
	}
	
	public ItemDTO findById(Long id) throws ItensNotFoundException{
		Optional<Item> item = itemRepository.findById(id);
		if(item.isPresent()) {
			return itensMapper.toDTO(item.get());
		}else {
			throw new ItensNotFoundException("Item with id " + id + " not found!");
		}
	}

	public ItemDTO save(ItemDTO itemDto) {
		itemRepository.save(Item.builder().name(itemDto.getName()).build());
		return itemDto;
	}

	public void deleteById(Long id) throws ItensNotFoundException{
		itemRepository.deleteById(id);
	}
	public ItemDTO updateItens(Long id, ItemDTO itemDtoDetails) throws ItensNotFoundException{
		Optional<Item> itemOpt = itemRepository.findById(id);
		if(itemOpt.isPresent()) {
			itemOpt.get().setName(itemDtoDetails.getName());
			itemRepository.save(itemOpt.get());
			return itemDtoDetails;
		}else {
			throw new ItensNotFoundException("Item with id " + id + " not found!");
		}
	}
}