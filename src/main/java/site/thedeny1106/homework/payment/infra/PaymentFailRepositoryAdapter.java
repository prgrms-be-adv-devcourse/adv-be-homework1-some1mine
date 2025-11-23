package site.thedeny1106.homework.payment.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.thedeny1106.homework.payment.doamin.PaymentFail;
import site.thedeny1106.homework.payment.doamin.PaymentFailRepository;

@Repository
@RequiredArgsConstructor
public class PaymentFailRepositoryAdapter implements PaymentFailRepository {
    private final PaymentFailJpaRepository paymentRepository;

    @Override
    public PaymentFail save(PaymentFail payment) {
        return paymentRepository.save(payment);
    }
}
