package kg.com.resource.controller;

import kg.com.resource.dto.RentalPriceDto;
import kg.com.resource.dto.requests.RentalPriceCreateRequest;
import kg.com.resource.service.RentalPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rental-prices")
@RequiredArgsConstructor
public class RentalPriceController {
	
	private final RentalPriceService rentalPriceService;
	
	@GetMapping
	public ResponseEntity<List<RentalPriceDto>> getAllRentalPrices() {
		return ResponseEntity.ok(rentalPriceService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RentalPriceDto> getRentalPriceById(@PathVariable Long id) {
		return ResponseEntity.ok(rentalPriceService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<RentalPriceDto> createRentalPrice(@RequestBody RentalPriceCreateRequest rentalPriceDTO) {
		return ResponseEntity.ok(rentalPriceService.save(rentalPriceDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RentalPriceDto> updateRentalPrice(@PathVariable Long id, @RequestBody RentalPriceDto rentalPriceDto) {
		return ResponseEntity.ok(rentalPriceService.update(id, rentalPriceDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRentalPrice(@PathVariable Long id) {
		rentalPriceService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
