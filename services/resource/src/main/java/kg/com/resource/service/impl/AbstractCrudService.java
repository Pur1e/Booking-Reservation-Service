package kg.com.resource.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kg.com.resource.service.mappers.DtoMapper;
import kg.com.resource.service.mappers.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class AbstractCrudService<DTO, ENTITY, REQUEST>
		implements DtoMapper<DTO, ENTITY>, EntityMapper<ENTITY, DTO> {
	
	private JpaRepository<ENTITY, Long> repository;
	
	public List<DTO> findAll() {
		return repository.findAll().stream()
				.map(this :: toDto)
				.toList();
	}
	
	public DTO findById(Long id) {
		ENTITY entity = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found"));
		return toDto(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		ENTITY entity = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found"));
		repository.delete(entity);
	}
	
	@Transactional
	public abstract DTO save(REQUEST request);
	
	@Transactional
	public abstract DTO update(Long id, DTO dto);
	
}
