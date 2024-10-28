package kg.com.resource.service;

import kg.com.resource.dto.ResourceDto;
import kg.com.resource.dto.requests.ResourceCreateRequest;

import java.util.List;

public interface ResourceService {
	
	List<ResourceDto> findAll();
	
	ResourceDto findById(Long id);
	
	ResourceDto save(ResourceCreateRequest resourceDTO);
	
	ResourceDto update(Long id, ResourceDto resourceDTO);
	
	void delete(Long id);
}
