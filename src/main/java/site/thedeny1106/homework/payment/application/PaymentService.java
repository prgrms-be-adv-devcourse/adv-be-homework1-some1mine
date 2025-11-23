package site.thedeny1106.homework.payment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import site.thedeny1106.homework.common.ResponseEntity;
import site.thedeny1106.homework.order.application.OrderService;
import site.thedeny1106.homework.order.domain.Order;
import site.thedeny1106.homework.payment.application.dto.PaymentCommand;
import site.thedeny1106.homework.payment.application.dto.PaymentFailCommand;
import site.thedeny1106.homework.payment.client.TossPaymentClient;
import site.thedeny1106.homework.payment.client.TossPaymentResponse;
import site.thedeny1106.homework.payment.doamin.Payment;
import site.thedeny1106.homework.payment.doamin.PaymentFail;
import site.thedeny1106.homework.payment.doamin.PaymentFailRepository;
import site.thedeny1106.homework.payment.doamin.PaymentRepository;
import site.thedeny1106.homework.payment.presentation.dto.PaymentFailInfo;
import site.thedeny1106.homework.payment.presentation.dto.PaymentInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentFailRepository paymentFailureRepository;
    private final TossPaymentClient tossPaymentClient;
    private final OrderService orderService;

    public ResponseEntity<List<PaymentInfo>> findAll(Pageable pageable) {
        Page<Payment> page = paymentRepository.findAll(pageable);
        List<PaymentInfo> result = page.stream().map(Payment::toResponse)
                .toList();

        return new ResponseEntity<>(HttpStatus.OK.value(), result, page.getTotalElements());
    }

    public ResponseEntity<PaymentInfo> confirm(PaymentCommand command) {
        TossPaymentResponse tossPayment = tossPaymentClient.confirm(command);
        UUID orderId = UUID.fromString(tossPayment.orderId());
        Order order = orderService.findById(orderId);
        Payment payment = Payment.create(
                tossPayment.paymentKey(),
                tossPayment.orderId(),
                tossPayment.totalAmount()
        );
        LocalDateTime approvedAt = tossPayment.approvedAt() != null ? tossPayment.approvedAt().toLocalDateTime() : null;
        LocalDateTime requestedAt = tossPayment.requestedAt() != null ? tossPayment.requestedAt().toLocalDateTime() : null;
        payment.markConfirmed(tossPayment.method(), approvedAt, requestedAt);

        Payment saved = paymentRepository.save(payment);
        orderService.makePaid(order);

        return new ResponseEntity<>(HttpStatus.CREATED.value(), saved.toResponse(), 1);
    }

    public ResponseEntity<PaymentFailInfo> recordFailure(PaymentFailCommand command) {
        PaymentFail saved = paymentFailureRepository.save(PaymentFail.from(command));
        return new ResponseEntity<>(HttpStatus.OK.value(), saved.toResponse(), 1);
    }
}
