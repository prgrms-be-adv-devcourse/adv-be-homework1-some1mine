package site.thedeny1106.homework.product.doamin;

import jakarta.persistence.*;
import lombok.Getter;
import site.thedeny1106.homework.product.application.dto.ProductCommand;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "\"product\"", schema = "public")
public class Product {

    @Id
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "reg_id", nullable = false)
    private UUID regId;

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "modify_id", nullable = false)
    private UUID modifyId;

    @Column(name = "modify_dt", nullable = false)
    private LocalDateTime modifyDt;

    private UUID creatorID;

    private UUID sellerId;

    protected Product() {
    }

    private Product(UUID id,
                    String name,
                    String description,
                    BigDecimal price,
                    Integer stock,
                    String status,
                    UUID creatorId,
                    UUID sellerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.creatorID = creatorId;
        this.sellerId = sellerId;
    }

    public static Product fromCommand(ProductCommand command) {
        Product product = new Product(UUID.randomUUID(),
                command.name(),
                command.description(),
                command.price(),
                command.stock(),
                command.status(),
                command.creatorID(),
                command.sellerID()
        );
        product.regId = command.creatorID();
        product.modifyId = command.creatorID();
        return product;
    }

    public void update(String name,
                       String description,
                       BigDecimal price,
                       Integer stock,
                       String status,
                       UUID modifierId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.modifyId = modifierId;
    }

    @PrePersist
    public void onCreate() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (regId == null) {
            regId = id;
        }
        if (modifyId == null) {
            modifyId = regId;
        }
        if (regDt == null) {
            regDt = LocalDateTime.now();
        }
        if (modifyDt == null) {
            modifyDt = regDt;
        }
        if (status == null) {
            status = "ACTIVE";
        }
        if (stock == null) {
            stock = 0;
        }
    }

    @PreUpdate
    public void onUpdate() {
        modifyDt = LocalDateTime.now();
        if (modifyId == null) {
            modifyId = id;
        }
    }

}