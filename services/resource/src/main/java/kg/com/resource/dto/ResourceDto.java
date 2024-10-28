package kg.com.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto {
	private String id, ownerId;
	private String name, location, description, status;
	private List<RentalPriceDto> rentalPriceDtoList;
	private CategoryDto categoryDto;
}
