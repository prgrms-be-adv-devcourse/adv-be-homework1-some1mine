package site.thedeny1106.homework.seller.presentation.dto;

import site.thedeny1106.homework.seller.doamin.Seller;

import java.time.LocalDateTime;
import java.util.UUID;

public record SellerInfo(
        UUID id,
        String companyName,
        String representativeName,
        String email,
        String phone,
        String businessNumber,
        String address,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){
}
