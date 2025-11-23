package site.thedeny1106.homework.payment.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import site.thedeny1106.homework.payment.doamin.Payment;
import site.thedeny1106.homework.payment.doamin.PaymentRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {
    private final PaymentJpaRepository paymentRepository;

    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
