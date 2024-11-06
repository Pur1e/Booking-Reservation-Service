package kg.com.resource.service;

import kg.com.resource.dto.CategoryDto;
import kg.com.resource.dto.requests.CategoryCreateRequest;

import java.util.List;

public interface CategoryService {

	List<CategoryDto> findAll();
	
	CategoryDto findById(Long id);
	
	CategoryDto save(CategoryCreateRequest category);
	
	CategoryDto update(Long id, CategoryDto categoryDTO);
	
	void delete(Long id);
}
