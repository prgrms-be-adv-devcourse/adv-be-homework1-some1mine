package site.thedeny1106.homework.seller.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import site.thedeny1106.homework.seller.doamin.Seller;
import site.thedeny1106.homework.seller.doamin.SellerRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SellerRepositoryAdapter implements SellerRepository {
    private final SellerJpaRepository sellerRepository;
    @Override
    public Page<Seller> findAll(Pageable pageable) {
        return sellerRepository.findAll(pageable);
    }

    @Override
    public Optional<Seller> findById(UUID id) {
        return sellerRepository.findById(id);
    }

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public void deleteById(UUID id) {
        sellerRepository.deleteById(id);
    }
}
