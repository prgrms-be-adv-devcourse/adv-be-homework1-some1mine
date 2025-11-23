package site.thedeny1106.homework.product.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import site.thedeny1106.homework.product.doamin.Product;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<Product, UUID> {
}
