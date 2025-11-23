package site.thedeny1106.homework.product.presentation.dto;

import site.thedeny1106.homework.product.doamin.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 외부에 노출되는 제품 응답 DTO.
 */
public record ProductInfo(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String status,
        LocalDateTime regDt,
        LocalDateTime modifyDt,
        UUID sellerId
) {

    public static ProductInfo from(Product product) {
        return new ProductInfo(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getRegDt(),
                product.getModifyDt(),
                product.getSellerId()
        );
    }
}
