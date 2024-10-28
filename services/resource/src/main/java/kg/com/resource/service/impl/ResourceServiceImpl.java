package kg.com.resource.service.impl;

import kg.com.resource.dto.ResourceDto;
import kg.com.resource.dto.requests.ResourceCreateRequest;
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
}
