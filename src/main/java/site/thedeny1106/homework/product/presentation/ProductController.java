package site.thedeny1106.homework.product.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import site.thedeny1106.homework.common.ResponseEntity;
import site.thedeny1106.homework.product.application.ProductService;
import site.thedeny1106.homework.product.presentation.dto.ProductInfo;
import site.thedeny1106.homework.product.presentation.dto.ProductRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1}/product")
public class ProductController {
    private final ProductService productService;
    @Operation(
            summary = "전체 회원 조회",
            description = "전체 회원 목록을 조회한다."
    )

    @GetMapping
    public ResponseEntity<List<ProductInfo>> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @Operation(
            summary = "회원 등록",
            description = "요청으로 받은 회원 정보를 public.product에 저장한다."
    )
    @PostMapping
    public ResponseEntity<ProductInfo> create(@RequestBody ProductRequest request) {
        return productService.create(request.toCommand());
    }

    @Operation(
            summary = "회원 정보 수정",
            description = "수정할 정보를 받아 public.product의 레코드를 수정한다."
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProductInfo> update(@RequestBody ProductRequest request, @PathVariable String id) {
        return productService.update(id, request.toCommand());
    }

    @Operation(
            summary = "회원 정보 삭제",
            description = "id로 회원 삭제"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return productService.delete(id);
    }
}

