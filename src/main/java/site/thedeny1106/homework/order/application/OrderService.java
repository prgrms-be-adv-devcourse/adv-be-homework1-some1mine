package site.thedeny1106.homework.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import site.thedeny1106.homework.common.ResponseEntity;
import site.thedeny1106.homework.order.application.dto.OrderCommand;
import site.thedeny1106.homework.order.domain.PurchaseOrder;
import site.thedeny1106.homework.order.domain.OrderRepository;
import site.thedeny1106.homework.order.application.dto.OrderInfo;
import site.thedeny1106.homework.order.domain.PurchaseOrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public ResponseEntity<OrderInfo> create(OrderCommand command) {
        return new ResponseEntity<>(HttpStatus.OK.value(), repository.save(PurchaseOrder.fromCommand(command)).toResponse(), 1);
    }

    public ResponseEntity<List<OrderInfo>> findAll(Pageable pageable) {
        Page<PurchaseOrder> result = repository.findAll(pageable);
        return new ResponseEntity<>(HttpStatus.OK.value(), result, result.getTotalElements());
    }

    public ResponseEntity<OrderInfo> setStatus(String id, PurchaseOrderStatus status) throws IllegalAccessException {
        Optional<PurchaseOrder> order = repository.findById(UUID.fromString(id));
        if (order.isPresent()) {
            PurchaseOrder item = order.get();
            item.setStatus(status);
            return new ResponseEntity<>(HttpStatus.OK.value(), repository.save(item).toResponse(), 1);
        }
        throw new IllegalAccessException("order not found id : " + id);
    }
}
