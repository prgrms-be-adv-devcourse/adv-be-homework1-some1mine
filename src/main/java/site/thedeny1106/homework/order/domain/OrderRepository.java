package site.thedeny1106.homework.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Page<Order> findAll(Pageable pageable);
    Order save(Order order);
    Optional<Order> findById(UUID id);
}
