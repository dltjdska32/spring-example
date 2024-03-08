package lsn.example.testproject.data.entity;

import lombok.*;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//클라이언트 -> 컨트롤러 -> 서비스 -> DAO(레퍼지토리) -> DB
//클라이언트 <- 컨트롤러 <- 서비스 <- DAO(레퍼지토리) <- DB
//클라이언트 -> 컨트롤러 -> 서비스, 클라이언트 <- 컨트롤러 <- 서비스 이부분에서 데이터이동은 DTO를 통해서 데이터 이동
//서비스 -> DAO(레퍼지토리) -> DB, 서비스 <- DAO(레퍼지토리) <- DB 이부분에서 데이터 이동은 Entity를 통해서 데이터 이동


// 데이터베이스의 테이블과 실질적으로 매핑되는 클래스
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 테이블 어노테이션 - entity를 기반으로 데이터베이스 테이블을 생성 (테이블 이름은 product)
@Table(name = "product")
public class   ProductEntity {

    // ID 어노테이션 -  productID를 프라이머리키로 설정
    @Id
    String productId;
    String productName;
    Integer productPrice;
    Integer productStock;

}
