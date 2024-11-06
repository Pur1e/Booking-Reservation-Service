package kg.com.resource.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kg.com.resource.dto.CategoryDto;
import kg.com.resource.dto.requests.CategoryCreateRequest;
import kg.com.resource.model.Category;
import kg.com.resource.repository.CategoryRepository;
import kg.com.resource.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends AbstractCrudService<CategoryDto, Category, CategoryCreateRequest>
		implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public CategoryDto save(CategoryCreateRequest categoryRequest) {
		Category category = Category.builder()
				.name(categoryRequest.getName())
				.parent(categoryRequest.getParentId() != null ? findByIdEntity(categoryRequest.getParentId()) : null)
				.build();
		
		Category savedCategory = categoryRepository.save(category);
		log.info("Category created with ID: {}", savedCategory.getId());
		return toDto(savedCategory);
	}
	
	@Override
	public CategoryDto update(Long id, CategoryDto dto) {
		Category existingCategory = findByIdEntity(id);
		
		existingCategory.setName(dto.getName());
		existingCategory.setParent(dto.getParentId() != null ? findByIdEntity(dto.getParentId()) : null);
		
		Category updatedCategory = categoryRepository.save(existingCategory);
		log.info("Category updated with ID: {}", updatedCategory.getId());
		return toDto(updatedCategory);
	}
	
	protected Category findByIdEntity(Long id) {
		return categoryRepository.findById(id).orElseThrow(() ->
				new EntityNotFoundException("Category Not Found"));
	}
	
	@Override
	public CategoryDto toDto(Category category) {
		Long parentId = category.getParent().getId();
		
		return CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.parentId(parentId)
				.build();
	}
	
	@Override
	public Category toEntity(CategoryDto dto) {
		Category parent = categoryRepository.findById(dto.getParentId()).orElse(null);
		
		return Category.builder()
				.id(dto.getId())
				.name(dto.getName())
				.parent(parent)
				.build();
	}
}
