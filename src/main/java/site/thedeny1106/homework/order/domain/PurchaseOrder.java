package site.thedeny1106.homework.order.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.thedeny1106.homework.order.application.dto.OrderCommand;
import site.thedeny1106.homework.order.application.dto.OrderInfo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"purchase_order\"", schema = "public")
public class PurchaseOrder {

    @Id
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "seller_id", nullable = false)
    private UUID sellerId;

    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PurchaseOrderStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static PurchaseOrder fromCommand(OrderCommand command) {
        return new PurchaseOrder(
                command.uuid(),
                command.productId() ,
                command.sellerId() ,
                command.memberId() ,
                command.amount() ,
                command.status(),
                command.now() ,
                command.now()
        );
    }

    public OrderInfo toResponse() {
        return new OrderInfo(
                id,
                productId,
                sellerId,
                memberId,
                amount,
                status,
                createdAt,
                updatedAt
        );
    }


    public void markPaid() {
        this.status = PurchaseOrderStatus.PAID;
    }

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (id == null) {
            id = UUID.randomUUID();
        }
        createdAt = now;
        updatedAt = now;
        if (status == null) {
            status = PurchaseOrderStatus.CREATED;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
