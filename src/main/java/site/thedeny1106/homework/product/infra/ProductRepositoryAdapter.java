package site.thedeny1106.homework.product.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import site.thedeny1106.homework.product.doamin.Product;
import site.thedeny1106.homework.product.doamin.ProductRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {
    private final ProductJpaRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
}
