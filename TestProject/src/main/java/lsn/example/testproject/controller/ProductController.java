package lsn.example.testproject.controller;

import lsn.example.testproject.common.Constant;
// import lsn.example.testproject.common.exception.LeeException;
import lsn.example.testproject.data.dto.ProductDto;
import lsn.example.testproject.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


//클라이언트 -> 컨트롤러 -> 서비스 -> DAO(레퍼지토리) -> DB
//클라이언트 <- 컨트롤러 <- 서비스 <- DAO(레퍼지토리) <- DB
//클라이언트 -> 컨트롤러 -> 서비스, 클라이언트 <- 컨트롤러 <- 서비스 이부분에서 데이터이동은 DTO를 통해서 데이터 이동
//서비스 -> DAO(레퍼지토리) -> DB, 서비스 <- DAO(레퍼지토리) <- DB 이부분에서 데이터 이동은 Entity를 통해서 데이터 이동


//컨트롤러는 사용자에게 요청을 받아서 서비스 클래스에 요청을 전달.
@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;
    //의존성 주입
     @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    // 사용자에게 프로덕트 아이디를 받아 해당 아이디와 일치하는 프로덕트 디티오를 찾아 반환한다.(프로덕트 디티오 클래스에는 id, name, price, stock이있다.)
    //http://localhost:8080/api/v1/product/{productId}
    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId) {

        long startTime = System.currentTimeMillis();
        LOGGER.info("[ProductController] perform {} of Lee API.", "getProduct");

        ProductDto productDto = productService.getProduct(productId);

        LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms",
                productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStock(), (System.currentTimeMillis() - startTime));
        return productService.getProduct(productId);
    }


    //http://localhost:8080/api/v1/product
    @PostMapping(value = "/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {

         //유효성 검사 예제(@valid 어노테이션을 쓰지 않은 예제) -> 가독성, 효율성이 떨어짐
      /*
      if(productDto.getProductId().equals("") || productDto.getProductId().isEmpty()) {

            LOGGER.error("[createProduct] failed Response :: productId is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
        }
     */
        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        ProductDto response = productService
                .saveProduct(productId, productName, productPrice, productStock);

        LOGGER.info(
                response.getProductId(), response.getProductName(), response.getProductName(),
                response.getProductStock());
        //  HTTP 응답을 생성하는 코드, HttpStatus.OK는 요청이 성공적으로 처리되었음을 나타냄
        //response는 응답 본문으로 전송될 객체
        //status() 함수는 ResponseEntity 객체의 상태 코드를 설정합니다. HttpStatus enum에서 정의된 다양한 상태 코드를 사용할 수 있습니다.
        //HttpStatus.OK: 요청 성공
        //HttpStatus.BAD_REQUEST: 잘못된 요청
        //HttpStatus.NOT_FOUND: 요청된 리소스가 존재하지 않음
        //HttpStatus.INTERNAL_SERVER_ERROR: 서버 오류
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    //http://localhost:8080/api/v1/product/{productId}
    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {return null; }


/*    //의도적으로 예외를 발생시키는 함수
    @PostMapping(value = "/product/exception")
    public void exceptionTest() throws LeeException {
         throw new LeeException(Constant.ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "의도한 에러 발생");
    }*/
}

