package kg.com.resource.service.impl;

import kg.com.resource.dto.CategoryDto;
import kg.com.resource.dto.requests.CategoryCreateRequest;
import kg.com.resource.repository.CategoryRepository;
import kg.com.resource.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDto> findAll() {
		return List.of();
	}
	
	@Override
	public CategoryDto findById(Long id) {
		return null;
	}
	
	@Override
	public CategoryDto save(CategoryCreateRequest category) {
		return null;
	}
	
	@Override
	public CategoryDto update(Long id, CategoryDto categoryDTO) {
		return null;
	}
	
	@Override
	public void delete(Long id) {
	
	}
}
