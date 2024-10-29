package kg.com.resource.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import kg.com.resource.dto.RentalPriceDto;
import kg.com.resource.dto.requests.RentalPriceCreateRequest;
import kg.com.resource.model.RentalPrice;
import kg.com.resource.model.Resource;
import kg.com.resource.repository.RentalPriceRepository;
import kg.com.resource.service.RentalPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalPriceServiceImpl implements RentalPriceService {
	
	private final RentalPriceRepository rentalPriceRepository;
	private final ResourceServiceImpl resourceService;
	
	@Override
	public List<RentalPriceDto> findAll() {
		List<RentalPriceDto> rentalPrices = rentalPriceRepository.findAll().stream()
				.map(this :: mapToDTO)
				.toList();
		log.info("Fetched {} rental prices", rentalPrices.size());
		return rentalPrices;
	}
	
	@Override
	public RentalPriceDto findById(Long id) {
		RentalPrice rentalPrice = rentalPriceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Rental price with ID " + id + " not found"));
		log.info("Fetched rental price with ID: {}", id);
		return mapToDTO(rentalPrice);
	}
	
	@Override
	@Transactional
	public RentalPriceDto save(RentalPriceCreateRequest rentalPriceRequest) {
		Resource resource = resourceService.findByIdEntity(rentalPriceRequest.getResourceId());
		
		RentalPrice rentalPrice = RentalPrice.builder()
				.rentType(rentalPriceRequest.getRentType())
				.price(rentalPriceRequest.getPrice())
				.currency(rentalPriceRequest.getCurrency())
				.resource(resource)
				.build();
		
		RentalPrice savedRentalPrice = rentalPriceRepository.save(rentalPrice);
		log.info("Created rental price with ID: {}", savedRentalPrice.getId());
		return mapToDTO(savedRentalPrice);
	}
	
	@Override
	@Transactional
	public RentalPriceDto update(Long id, RentalPriceDto rentalPriceDto) {
		RentalPrice existingRentalPrice = rentalPriceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Rental price with ID " + id + " not found"));
		
		Resource resource = resourceService.findByIdEntity(rentalPriceDto.getResourceId());
		
		existingRentalPrice.setRentType(rentalPriceDto.getRentType());
		existingRentalPrice.setPrice(rentalPriceDto.getPrice());
		existingRentalPrice.setCurrency(rentalPriceDto.getCurrency());
		existingRentalPrice.setResource(resource);
		
		RentalPrice updatedRentalPrice = rentalPriceRepository.save(existingRentalPrice);
		log.info("Updated rental price with ID: {}", updatedRentalPrice.getId());
		return mapToDTO(updatedRentalPrice);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		RentalPrice rentalPrice = rentalPriceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Rental price with ID " + id + " not found"));
		
		rentalPriceRepository.delete(rentalPrice);
		log.info("Deleted rental price with ID: {}", id);
	}
	
	
	protected RentalPrice mapToEntity(@NotNull RentalPriceDto dto) {
		Resource resource = resourceService.findByIdEntity(dto.getResourceId());
		return RentalPrice.builder()
				.id(dto.getId())
				.rentType(dto.getRentType())
				.price(dto.getPrice())
				.currency(dto.getCurrency())
				.resource(resource)
				.build();
	}
	
	protected RentalPriceDto mapToDTO(@NotNull RentalPrice rentalPrice) {
		Long resourceId = rentalPrice.getResource().getId();
		
		return RentalPriceDto.builder()
				.id(rentalPrice.getId())
				.rentType(rentalPrice.getRentType())
				.price(rentalPrice.getPrice())
				.currency(rentalPrice.getCurrency())
				.resourceId(resourceId)
				.build();
	}
}
