package kg.com.resource.service.impl;

import jakarta.persistence.EntityNotFoundException;
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
import org.springframework.transaction.annotation.Transactional;

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
		List<ResourceDto> resourceList = resourceRepository.findAll().stream()
				.map(this::mapToDTO)
				.toList();
		log.info("Fetched {} resources", resourceList.size());
		return resourceList;
	}
	
	@Override
	public ResourceDto findById(Long id) {
		Resource resource = resourceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Resource with ID " + id + " not found"));
		log.info("Fetched resource with ID: {}", id);
		return mapToDTO(resource);
	}
	
	@Override
	@Transactional
	public ResourceDto save(ResourceCreateRequest resourceRequest) {
		Category category = categoryService.findByIdEntity(resourceRequest.getCategoryId());
		
		Resource resource = Resource.builder()
				.name(resourceRequest.getName())
				.description(resourceRequest.getDescription())
				.location(resourceRequest.getLocation())
				.status(resourceRequest.getStatus())
				.ownerId(resourceRequest.getOwnerId())
				.category(category)
				.build();
		
		Resource savedResource = resourceRepository.save(resource);
		
		resourceRequest.getRentalPrices().forEach(
				rentalPriceService :: save
		);
		
		log.info("Created resource with ID: {}", savedResource.getId());
		return mapToDTO(savedResource);
	}
	
	@Override
	@Transactional
	public ResourceDto update(Long id, ResourceDto resourceDTO) {
		Resource existingResource = resourceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Resource with ID " + id + " not found"));
		
		Category category = categoryService.findByIdEntity(resourceDTO.getCategoryDto().getId());
		
		existingResource.setName(resourceDTO.getName());
		existingResource.setDescription(resourceDTO.getDescription());
		existingResource.setLocation(resourceDTO.getLocation());
		existingResource.setStatus(resourceDTO.getStatus());
		existingResource.setOwnerId(resourceDTO.getOwnerId());
		existingResource.setCategory(category);
		
		Resource updatedResource = resourceRepository.save(existingResource);
		log.info("Updated resource with ID: {}", updatedResource.getId());
		return mapToDTO(updatedResource);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		Resource resource = resourceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Resource with ID " + id + " not found"));
		
		resourceRepository.delete(resource);
		log.info("Deleted resource with ID: {}", id);
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
