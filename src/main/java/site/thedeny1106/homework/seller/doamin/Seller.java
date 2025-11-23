package site.thedeny1106.homework.seller.doamin;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.thedeny1106.homework.seller.presentation.dto.SellerInfo;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "\"seller\"", schema = "public")
public class Seller {

    @Id
    private UUID id;

    private String companyName;

    private String representativeName;
    private String email;
    private String phone;
    private String businessNumber;
    private String address;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Seller(UUID id, String companyName, String representativeName, String email, String phone, String businessNumber, String address, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.companyName = companyName;
        this.representativeName = representativeName;
        this.email = email;
        this.phone = phone;
        this.businessNumber = businessNumber;
        this.address = address;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Seller create(String companyName, String representativeName, String email, String phone, String businessNumber, String address, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Seller(
                UUID.randomUUID(),
                companyName,
                representativeName,
                email,
                phone,
                businessNumber,
                address,
                status,
                createdAt,
                updatedAt
        );
    }

    public SellerInfo toResponse() {
        return new SellerInfo(
                id,
                companyName,
                representativeName,
                email,
                phone,
                businessNumber,
                address,
                status,
                createdAt,
                updatedAt
        );
    }
}
