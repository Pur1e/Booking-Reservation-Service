package kg.com.resource.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceCreateRequest implements Serializable {
	
	@NotNull(message = "Name cannot be null")
	@Size(max = 100, message = "Name cannot exceed 100 characters")
	private String name;
	
	@Size(max = 1000, message = "Description cannot exceed 1000 characters")
	private String description;
	
	@Size(max = 100, message = "Location cannot exceed 100 characters")
	private String location;
	
	@Pattern(regexp = "ACTIVE|INACTIVE", message = "Status must be 'ACTIVE' or 'INACTIVE'")
	private String status;
	
	@NotNull(message = "Owner ID cannot be null")
	private Long ownerId;
	
	private Long categoryId;
	
	private Set<RentalPriceCreateRequest> rentalPrices;
}