package site.thedeny1106.homework.payment.doamin;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.thedeny1106.homework.payment.application.dto.PaymentFailCommand;
import site.thedeny1106.homework.payment.presentation.dto.PaymentFailInfo;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "\"payment_failure\"", schema = "public")
public class PaymentFail {

    @Id
    private UUID id;

    @Column(name = "order_id", nullable = false, length = 100)
    private String orderId;

    @Column(name = "payment_key", length = 200)
    private String paymentKey;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "amount")
    private Long amount;

    @Lob
    @Column(name = "raw_payload", columnDefinition = "TEXT")
    private String rawPayload;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    private PaymentFail(String orderId,
                           String paymentKey,
                           String errorCode,
                           String errorMessage,
                           Long amount,
                           String rawPayload) {
        this.id = UUID.randomUUID();
        this.orderId = orderId;
        this.paymentKey = paymentKey;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.amount = amount;
        this.rawPayload = rawPayload;
    }

    public static PaymentFail from(PaymentFailCommand command) {
        return new PaymentFail(
                command.orderId(),
                command.paymentKey(),
                command.errorCode(),
                command.errorMessage(),
                command.amount(),
                command.rawPayload()
        );
    }
    public PaymentFailInfo toResponse() {
        return new PaymentFailInfo(
                id,
                orderId,
                paymentKey,
                errorCode,
                errorMessage,
                amount,
                createdAt
        );
    }

    @PrePersist
    public void onCreate() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        createdAt = LocalDateTime.now();
    }
}