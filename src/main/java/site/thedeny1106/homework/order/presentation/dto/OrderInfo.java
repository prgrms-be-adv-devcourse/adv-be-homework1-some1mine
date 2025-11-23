package site.thedeny1106.homework.order.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import site.thedeny1106.homework.order.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderInfo {

    private UUID id;

    private UUID productId;

    private UUID sellerId;

    private UUID memberId;

    private BigDecimal amount;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
