package site.thedeny1106.homework.seller.application.dto;

import java.time.LocalDateTime;

public record SellerCommand(String companyName,
                            String representativeName,
                            String email,
                            String phone,
                            String businessNumber,
                            String address,
                            String status,
                            LocalDateTime createdAt,
                            LocalDateTime updatedAt) {
}
