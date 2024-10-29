package kg.com.resource.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import kg.com.resource.dto.CategoryDto;
import kg.com.resource.dto.requests.CategoryCreateRequest;
import kg.com.resource.model.Category;
import kg.com.resource.repository.CategoryRepository;
import kg.com.resource.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final ResourceServiceImpl resourceRepository;
	
	@Override
	public List<CategoryDto> findAll() {
		return categoryRepository.findAll().stream()
				.map(this :: mapToDTO)
				.toList();
	}
	
	@Override
	public CategoryDto findById(Long id) {
		return mapToDTO(categoryRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found")));
	}
	
	@Override
	@Transactional
	public CategoryDto save(CategoryCreateRequest categoryRequest) {
		Category category = Category.builder()
				.name(categoryRequest.getName())
				.parent(categoryRequest.getParentId() != null ? findByIdEntity(categoryRequest.getParentId()) : null)
				.build();
		
		Category savedCategory = categoryRepository.save(category);
		log.info("Category created with ID: {}", savedCategory.getId());
		return mapToDTO(savedCategory);
	}
	
	@Override
	@Transactional
	public CategoryDto update(Long id, CategoryDto categoryDTO) {
		Category existingCategory = findByIdEntity(id);
		
		existingCategory.setName(categoryDTO.getName());
		existingCategory.setParent(categoryDTO.getParentId() != null ? findByIdEntity(categoryDTO.getParentId()) : null);
		
		Category updatedCategory = categoryRepository.save(existingCategory);
		log.info("Category updated with ID: {}", updatedCategory.getId());
		return mapToDTO(updatedCategory);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		Category category = findByIdEntity(id);
		categoryRepository.delete(category);
		log.info("Category deleted with ID: {}", id);
	}
	
	protected Category findByIdEntity(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category Not Found"));
	}
	
	protected Category mapToEntity(@NotNull CategoryDto dto) {
		Category parent = categoryRepository.findById(dto.getParentId()).orElse(null);
		return Category.builder()
				.id(dto.getId())
				.name(dto.getName())
				.parent(parent)
				.build();
	}
	
	protected CategoryDto mapToDTO(@NotNull Category category) {
		Long parentId = category.getParent().getId();
		
		return CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.parentId(parentId)
				.build();
	}
}
