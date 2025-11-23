package site.thedeny1106.homework.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import site.thedeny1106.homework.common.ResponseEntity;
import site.thedeny1106.homework.product.application.dto.ProductCommand;
import site.thedeny1106.homework.product.doamin.Product;
import site.thedeny1106.homework.product.doamin.ProductRepository;
import site.thedeny1106.homework.product.presentation.dto.ProductInfo;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public ResponseEntity<List<ProductInfo>> findAll(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        List<ProductInfo> result =  productRepository.findAll(pageable).stream()
                .map(ProductInfo::from)
                .toList();
        return new ResponseEntity<>(HttpStatus.OK.value(), result, page.getTotalElements());
    }

    public ResponseEntity<ProductInfo> create(ProductCommand command) {
        ProductInfo result = ProductInfo.from(productRepository.save(Product.fromCommand(command)));
        return new ResponseEntity<>(HttpStatus.OK.value(), result, 1);
    }

    public ResponseEntity<ProductInfo> update(String id, ProductCommand command) {
        Product found = productRepository.findById(UUID.fromString(id)).orElse(null);
        if (found == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND.value(), null, 0);
        ProductInfo result = ProductInfo.from(Product.fromCommand(command));

        return new ResponseEntity<>(HttpStatus.OK.value(), result, 1);
    }

    public ResponseEntity<?> delete(String id) {
        productRepository.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK.value(), 1, 1);
    }
}
