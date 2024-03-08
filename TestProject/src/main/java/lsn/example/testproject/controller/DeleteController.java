package lsn.example.testproject.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/get-api")
public class DeleteController {

    //http://localhost:8080/api/v1/get-api/variable/{String 값}
    @DeleteMapping(value = "/delete/{variable}")
    //현재는 데이터를 삭제하는 로직이 없이 테스트를 위한 반환값만 보여줌
    //클라이언트에서 보낸 값을 받아 일치하는 값을 찾아 데이터베이스에서 삭제하고 성공하면 값을 반환하는 로직을 만들어야 함
    public String DeleteVariable(@PathVariable String variable) {return variable;}
}
