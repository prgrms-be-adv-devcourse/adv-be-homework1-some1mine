package site.thedeny1106.homework.payment.presentation.dto;


import site.thedeny1106.homework.payment.doamin.PaymentFail;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 결제 실패 응답 DTO.
 */
public record PaymentFailInfo(
        UUID id,
        String orderId,
        String paymentKey,
        String errorCode,
        String errorMessage,
        Long amount,
        LocalDateTime createdAt
) {
}

