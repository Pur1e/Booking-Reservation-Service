package kg.com.resource.service;

import kg.com.resource.dto.RentalPriceDto;
import kg.com.resource.dto.requests.RentalPriceCreateRequest;

import java.util.List;

public interface RentalPriceService {
	List<RentalPriceDto> findAll();
	
	RentalPriceDto findById(Long id);
	
	RentalPriceDto save(RentalPriceCreateRequest rentalPriceDTO);
	
	RentalPriceDto update(Long id, RentalPriceDto rentalPriceDTO);
	
	void delete(Long id);
}
