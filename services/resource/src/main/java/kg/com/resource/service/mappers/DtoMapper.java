package kg.com.resource.service.mappers;

public interface DtoMapper<DTO, ENTITY> {
	DTO toDto(ENTITY entity);
}
