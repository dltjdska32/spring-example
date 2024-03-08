package lsn.example.testproject.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



//클라이언트 -> 컨트롤러 -> 서비스 -> DAO(레퍼지토리) -> DB
//클라이언트 <- 컨트롤러 <- 서비스 <- DAO(레퍼지토리) <- DB
//클라이언트 -> 컨트롤러 -> 서비스, 클라이언트 <- 컨트롤러 <- 서비스 이부분에서 데이터이동은 DTO를 통해서 데이터 이동
//서비스 -> DAO(레퍼지토리) -> DB, 서비스 <- DAO(레퍼지토리) <- DB 이부분에서 데이터 이동은 Entity를 통해서 데이터 이동
@Getter
@Setter
@ToString
public class MemberDTO {
    private String name;
    private String email;
    private String organization;
}
