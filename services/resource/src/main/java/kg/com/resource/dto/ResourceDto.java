package kg.com.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto {
	private Long id, ownerId;
	private String name, location, description, status, currency;
	private BigDecimal price;
	private CategoryDto categoryDto;
}
