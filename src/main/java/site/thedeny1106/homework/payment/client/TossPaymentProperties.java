package site.thedeny1106.homework.payment.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "payment.toss")
public class TossPaymentProperties {

    /**
     * 결제 승인 요청 시 사용되는 secret key.
     */
    private String secretKey;

    /**
     * 프론트 성공 리다이렉트 URL.
     */
    private String successUrl;

    /**
     * 프론트 실패 리다이렉트 URL.
     */
    private String failUrl;

}

