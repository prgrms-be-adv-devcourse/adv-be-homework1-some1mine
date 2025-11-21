package site.thedeny1106.homework.order.application.dto;

import site.thedeny1106.homework.order.domain.PurchaseOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderCommand(
        UUID uuid,
        UUID productId,
        UUID sellerId,
        UUID memberId,
        BigDecimal amount,
        PurchaseOrderStatus status,
        LocalDateTime now,
        LocalDateTime now1
) {
}
