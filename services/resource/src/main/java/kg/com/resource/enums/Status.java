package kg.com.resource.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	ACTIVE("ACTIVE"),
	INACTIVE("INACTIVE");
	
	private final String value;
}
