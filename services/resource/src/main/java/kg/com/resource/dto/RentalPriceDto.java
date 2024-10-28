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
public class RentalPriceDto {
	private Long id, resourceId;
	private String rentType, currency;
	private BigDecimal price;
}
