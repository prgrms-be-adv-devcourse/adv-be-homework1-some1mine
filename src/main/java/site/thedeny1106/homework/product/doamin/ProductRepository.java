package site.thedeny1106.homework.product.doamin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(UUID id);

    Product save(Product product);

    void deleteById(UUID id);
}
