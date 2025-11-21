package site.thedeny1106.homework.order.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import site.thedeny1106.homework.order.domain.PurchaseOrder;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<PurchaseOrder, UUID> {
}
