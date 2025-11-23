package site.thedeny1106.homework.seller.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import site.thedeny1106.homework.seller.doamin.Seller;

import java.util.UUID;

public interface SellerJpaRepository extends JpaRepository<Seller, UUID> {
}
