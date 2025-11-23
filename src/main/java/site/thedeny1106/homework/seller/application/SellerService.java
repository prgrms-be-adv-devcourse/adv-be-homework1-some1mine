package site.thedeny1106.homework.seller.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import site.thedeny1106.homework.common.ResponseEntity;
import site.thedeny1106.homework.seller.application.dto.SellerCommand;
import site.thedeny1106.homework.seller.doamin.Seller;
import site.thedeny1106.homework.seller.doamin.SellerRepository;
import site.thedeny1106.homework.seller.presentation.dto.SellerInfo;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;

    public ResponseEntity<List<SellerInfo>> findAll(Pageable pageable) {
        Page<Seller> page = sellerRepository.findAll(pageable);
        List<SellerInfo> sellers = page.stream()
                .map(Seller::toResponse)
                .toList();
        return new ResponseEntity<>(HttpStatus.OK.value(), sellers, page.getTotalElements());
    }

    public ResponseEntity<SellerInfo> create(SellerCommand command) {
        Seller seller = Seller.create(
                command.companyName(),
                command.representativeName(),
                command.email(),
                command.phone(),
                command.businessNumber(),
                command.address(),
                command.status(),
                command.createdAt(),
                command.updatedAt()

        );
        SellerInfo response = sellerRepository.save(seller).toResponse();

        return new ResponseEntity<>(HttpStatus.OK.value(), response, 1);
    }

    public ResponseEntity<SellerInfo> update(String id, SellerCommand command) {
        Seller found = sellerRepository.findById(UUID.fromString(id)).orElse(null);
        if (found == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND.value(), null, 0);

        Seller seller = Seller.create(
                command.companyName(),
                command.representativeName(),
                command.email(),
                command.phone(),
                command.businessNumber(),
                command.address(),
                command.status(),
                command.createdAt(),
                command.updatedAt()
        );

        SellerInfo response = sellerRepository.save(seller).toResponse();

        return new ResponseEntity<>(HttpStatus.OK.value(), response, 1);
    }

    public ResponseEntity<?> delete(String id) {
        sellerRepository.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK.value(), 1, 1);
    }
}