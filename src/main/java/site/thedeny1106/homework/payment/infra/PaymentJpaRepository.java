package site.thedeny1106.homework.payment.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import site.thedeny1106.homework.payment.doamin.Payment;

import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<Payment, UUID> {
}
