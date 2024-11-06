package kg.com.resource.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kg.com.resource.dto.CategoryDto;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl extends AbstractCrudService<ResourceDto, Resource, ResourceCreateRequest>
		implements ResourceService {
	
	private final ResourceRepository resourceRepository;
	private final CategoryServiceImpl categoryService;
	
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
		
		
		log.info("Created resource with ID: {}", savedResource.getId());
		return toDto(savedResource);
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
		return toDto(updatedResource);
	}
	
	@Override
	public ResourceDto toDto(Resource resource) {
		CategoryDto cDto = categoryService.toDto(resource.getCategory());
		
		return ResourceDto.builder()
				.id(resource.getId())
				.name(resource.getName())
				.description(resource.getDescription())
				.location(resource.getLocation())
				.status(resource.getStatus())
				.ownerId(resource.getOwnerId())
				.price(resource.getPrice())
				.currency(resource.getCurrency())
				.categoryDto(cDto)
				.build();
	}
	
	@Override
	public Resource toEntity(ResourceDto dto) {
		Category c = categoryService.findByIdEntity(dto.getCategoryDto().getId());
		return Resource.builder()
				.id(dto.getId())
				.name(dto.getName())
				.description(dto.getDescription())
				.location(dto.getLocation())
				.status(dto.getStatus())
				.ownerId(dto.getOwnerId())
				.currency(dto.getCurrency())
				.price(dto.getPrice())
				.category(c)
				.build();
	}
	
}
