package kg.com.resource.service.impl;

import kg.com.resource.dto.RentalPriceDto;
import kg.com.resource.dto.requests.RentalPriceCreateRequest;
import kg.com.resource.repository.RentalPriceRepository;
import kg.com.resource.service.RentalPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalPriceServiceImpl implements RentalPriceService {
	
	private final RentalPriceRepository rentalPriceRepository;
	private final CategoryServiceImpl categoryService;
	private final ResourceServiceImpl resourceService;
	
	@Override
	public List<RentalPriceDto> findAll() {
		return List.of();
	}
	
	@Override
	public RentalPriceDto findById(Long id) {
		return null;
	}
	
	@Override
	public RentalPriceDto save(RentalPriceCreateRequest rentalPriceDTO) {
		return null;
	}
	
	@Override
	public RentalPriceDto update(Long id, RentalPriceDto rentalPriceDTO) {
		return null;
	}
	
	@Override
	public void delete(Long id) {
	
	}
}
