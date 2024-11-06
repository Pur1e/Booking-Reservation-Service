package kg.com.resource.controller;

import kg.com.resource.dto.ResourceDto;
import kg.com.resource.dto.requests.ResourceCreateRequest;
import kg.com.resource.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources")
@RequiredArgsConstructor
public class ResourceController {
	
	private final ResourceService resourceService;
	
	@GetMapping
	public ResponseEntity<List<ResourceDto>> getAllResources() {
		return ResponseEntity.ok(resourceService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResourceDto> getResourceById(@PathVariable Long id) {
		return ResponseEntity.ok(resourceService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<ResourceDto> createResource(@RequestBody ResourceCreateRequest resourceDTO) {
		return ResponseEntity.ok(resourceService.save(resourceDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResourceDto> updateResource(@PathVariable Long id, @RequestBody ResourceDto resourceDTO) {
		return ResponseEntity.ok(resourceService.update(id, resourceDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
		resourceService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
