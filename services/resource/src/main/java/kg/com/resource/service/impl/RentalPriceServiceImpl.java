package kg.com.resource.service.impl;

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
