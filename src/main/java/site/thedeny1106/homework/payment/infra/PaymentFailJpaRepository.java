package site.thedeny1106.homework.payment.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import site.thedeny1106.homework.payment.doamin.Payment;
import site.thedeny1106.homework.payment.doamin.PaymentFail;

import java.util.UUID;

public interface PaymentFailJpaRepository extends JpaRepository<PaymentFail, UUID> {
}
