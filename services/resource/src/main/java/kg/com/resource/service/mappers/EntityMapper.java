package kg.com.resource.service.mappers;

public interface EntityMapper<ENTITY, DTO> {
	ENTITY toEntity(DTO dto);
}
