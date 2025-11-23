package site.thedeny1106.homework.order.presentation.dto;

import site.thedeny1106.homework.order.application.dto.OrderCommand;
import site.thedeny1106.homework.order.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderRequest(
        String id,

        String productId,

        String sellerId,

        String memberId,

        int amount,

        OrderStatus status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
    public OrderCommand toCommand() {
        return new OrderCommand(
                UUID.randomUUID(),
                productId != null ? UUID.fromString(productId) : UUID.randomUUID(),
                sellerId != null ? UUID.fromString(sellerId) : UUID.randomUUID(),
                memberId != null ? UUID.fromString(memberId) : UUID.randomUUID(),
                new BigDecimal(amount),
                status,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
