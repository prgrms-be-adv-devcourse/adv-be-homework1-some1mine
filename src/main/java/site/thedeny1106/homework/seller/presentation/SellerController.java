package site.thedeny1106.homework.seller.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import site.thedeny1106.homework.common.ResponseEntity;
import site.thedeny1106.homework.seller.application.SellerService;
import site.thedeny1106.homework.seller.presentation.dto.SellerInfo;
import site.thedeny1106.homework.seller.presentation.dto.SellerRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1}/seller")
public class SellerController {
    private final SellerService sellerService;


    @Operation(summary = "판매자 목록 조회", description = "등록된 판매자를 페이지 단위로 조회한다.")
    @GetMapping
    public ResponseEntity<List<SellerInfo>> findAll(Pageable pageable) {
        return sellerService.findAll(pageable);
    }

    @Operation(summary = "판매자 등록", description = "판매자 정보를 등록한다.")
    @PostMapping
    public ResponseEntity<SellerInfo> create(@RequestBody SellerRequest request) {
        return sellerService.create(request.toCommand());
    }

    @Operation(summary = "판매자 수정", description = "판매자 정보를 수정한다.")
    @PutMapping("{id}")
    public ResponseEntity<SellerInfo> update(@PathVariable("id") String id,
                                             @RequestBody SellerRequest request) {
        return sellerService.update(id, request.toCommand());
    }

    @Operation(summary = "판매자 삭제", description = "판매자를 삭제한다.")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return sellerService.delete(id);
    }
}
