package site.thedeny1106.homework.payment.presentation.dto;

import site.thedeny1106.homework.payment.doamin.Payment;
import site.thedeny1106.homework.payment.doamin.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 결제 응답 DTO.
 */
public record PaymentInfo(
        UUID id,
        String orderId,
        String paymentKey,
        Long amount,
        PaymentStatus status,
        String method,
        LocalDateTime requestedAt,
        LocalDateTime approvedAt,
        String failReason
) {


}

