package site.thedeny1106.homework.order.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import site.thedeny1106.homework.order.domain.OrderRepository;
import site.thedeny1106.homework.order.domain.PurchaseOrder;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Page<PurchaseOrder> findAll(Pageable pageable) {
        return orderJpaRepository.findAll(pageable);
    }

    @Override
    public PurchaseOrder save(PurchaseOrder order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public Optional<PurchaseOrder> findById(UUID id) {
        return orderJpaRepository.findById(id);
    }
}
