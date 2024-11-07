package kg.com.resource.controller;

import kg.com.resource.dto.CategoryDto;
import kg.com.resource.dto.requests.CategoryCreateRequest;
import kg.com.resource.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		return ResponseEntity.ok(categoryService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreateRequest category) {
		return ResponseEntity.ok(categoryService.save(category));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDTO) {
		return ResponseEntity.ok(categoryService.update(id, categoryDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}