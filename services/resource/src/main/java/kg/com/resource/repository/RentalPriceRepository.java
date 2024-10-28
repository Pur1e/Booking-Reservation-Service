package kg.com.resource.repository;

import kg.com.resource.model.RentalPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RentalPriceRepository extends JpaRepository<RentalPrice, Long> {
}
