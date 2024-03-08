package lsn.example.testproject.data.dto;

import lombok.*;
import lsn.example.testproject.data.entity.ProductEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//클라이언트 -> 컨트롤러 -> 서비스 -> DAO(레퍼지토리) -> DB
//클라이언트 <- 컨트롤러 <- 서비스 <- DAO(레퍼지토리) <- DB
//클라이언트 -> 컨트롤러 -> 서비스, 클라이언트 <- 컨트롤러 <- 서비스 이부분에서 데이터이동은 DTO를 통해서 데이터 이동
//서비스 -> DAO(레퍼지토리) -> DB, 서비스 <- DAO(레퍼지토리) <- DB 이부분에서 데이터 이동은 Entity를 통해서 데이터 이동

//각 계층간에 데이터를 전달하는 역할
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {

    @NotNull
//    @Size(min = 8, max = 8)
    private String productId;

    @NotNull
    private String productName;

    @NotNull
    @Min(value = 500)
    @Max(value = 3000000)
    private int productPrice;

    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
    private int productStock;

    //디티오 값을 엔터티로 변환
    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
    }
}
