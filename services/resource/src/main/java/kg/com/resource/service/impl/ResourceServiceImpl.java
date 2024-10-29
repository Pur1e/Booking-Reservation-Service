package kg.com.resource.service.impl;

import jakarta.validation.constraints.NotNull;
import kg.com.resource.dto.CategoryDto;
import kg.com.resource.dto.RentalPriceDto;
import kg.com.resource.dto.ResourceDto;
import kg.com.resource.dto.requests.ResourceCreateRequest;
import kg.com.resource.model.Category;
import kg.com.resource.model.Resource;
import kg.com.resource.repository.ResourceRepository;
import kg.com.resource.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
	
	private final ResourceRepository resourceRepository;
	private final CategoryServiceImpl categoryService;
	private final RentalPriceServiceImpl rentalPriceService;
	
	@Override
	public List<ResourceDto> findAll() {
		return List.of();
	}
	
	@Override
	public ResourceDto findById(Long id) {
		return null;
	}
	
	@Override
	public ResourceDto save(ResourceCreateRequest resourceDTO) {
		return null;
	}
	
	@Override
	public ResourceDto update(Long id, ResourceDto resourceDTO) {
		return null;
	}
	
	@Override
	public void delete(Long id) {
	
	}
	
	protected Resource findByIdEntity(@NotNull Long idEntity) {
		return resourceRepository.findById(idEntity).orElse(null);
	}
	
	protected Resource mapToEntity(@NotNull ResourceDto dto) {
		Category c = categoryService.findByIdEntity(dto.getCategoryDto().getId());
		return Resource.builder()
				.id(dto.getId())
				.name(dto.getName())
				.description(dto.getDescription())
				.location(dto.getLocation())
				.status(dto.getStatus())
				.ownerId(dto.getOwnerId())
				.category(c)
				.build();
	}
	
	protected ResourceDto mapToDTO(@NotNull Resource resource) {
		List<RentalPriceDto> rentalPriceList = resource
				.getRentalPrices()
				.stream()
				.map(rentalPriceService :: mapToDTO)
				.toList();
		
		CategoryDto cDto = categoryService.mapToDTO(resource.getCategory());
		
		return ResourceDto.builder()
				.id(resource.getId())
				.name(resource.getName())
				.description(resource.getDescription())
				.location(resource.getLocation())
				.status(resource.getStatus())
				.ownerId(resource.getOwnerId())
				.categoryDto(cDto)
				.rentalPriceDtoList(rentalPriceList)
				.build();
	}
}
