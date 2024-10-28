package kg.com.resource.dto.requests;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalPriceCreateRequest implements Serializable {

	@NotNull(message = "Rent type cannot be null")
	@Pattern(regexp = "HOURLY|DAILY|MONTHLY", message = "Rent type must be 'HOURLY', 'DAILY', or 'MONTHLY'")
	private String rentType;
	
	@NotNull(message = "Price cannot be null")
	@Digits(integer = 8, fraction = 2, message = "Price must be a valid decimal with up to 8 integer and 2 fraction digits")
	private BigDecimal price;
	
	@NotNull(message = "Currency cannot be null")
	@Pattern(regexp = "USD|KGS|RUB", message = "Currency must be 'USD', 'KGS', or 'RUB'")
	private String currency;
	
	@NotNull(message = "Resource ID cannot be null")
	private Long resourceId;
}
