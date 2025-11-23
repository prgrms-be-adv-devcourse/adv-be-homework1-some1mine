package site.thedeny1106.homework.order.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import site.thedeny1106.homework.order.domain.Order;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<Order, UUID> {
}
