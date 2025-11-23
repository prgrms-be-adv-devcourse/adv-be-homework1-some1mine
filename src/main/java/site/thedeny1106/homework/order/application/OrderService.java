package site.thedeny1106.homework.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import site.thedeny1106.homework.common.ResponseEntity;
import site.thedeny1106.homework.order.application.dto.OrderCommand;
import site.thedeny1106.homework.order.domain.Order;
import site.thedeny1106.homework.order.domain.OrderRepository;
import site.thedeny1106.homework.order.presentation.dto.OrderInfo;
import site.thedeny1106.homework.order.domain.OrderStatus;
import site.thedeny1106.homework.product.doamin.Product;
import site.thedeny1106.homework.product.doamin.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<OrderInfo> create(OrderCommand command) throws IllegalAccessException {
        if (command.productId() == null || command.memberId() == null)
            throw new IllegalAccessException("productID and memberID required");
        Product product = productRepository.findById(command.productId())
                .orElseThrow(() -> new IllegalAccessException("product not found:" + command.productId()));
        Order order = Order.create(product.getId(), product.getSellerId(), command.memberId(), product.getPrice());
        return new ResponseEntity<>(HttpStatus.OK.value(), orderRepository.save(order).toResponse(), 1);
    }

    public ResponseEntity<List<OrderInfo>> findAll(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        List<OrderInfo> result = page.stream().map(Order::toResponse).toList();
        return new ResponseEntity<>(HttpStatus.OK.value(), result, page.getTotalElements());
    }

    public ResponseEntity<OrderInfo> setStatus(String id, OrderStatus status) throws IllegalAccessException {
        Optional<Order> order = orderRepository.findById(UUID.fromString(id));
        if (order.isPresent()) {
            Order item = order.get();
            item.setStatus(status);
            return new ResponseEntity<>(HttpStatus.OK.value(), orderRepository.save(item).toResponse(), 1);
        }
        throw new IllegalAccessException("order not found id : " + id);
    }

    public Order findById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found : " + orderId));
    }

    public void makePaid(Order order) {
        order.markPaid();
        orderRepository.save(order);
    }
}
