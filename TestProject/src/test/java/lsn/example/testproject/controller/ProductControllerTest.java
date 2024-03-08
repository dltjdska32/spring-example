package lsn.example.testproject.controller;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import lsn.example.testproject.data.dto.ProductDto;
import lsn.example.testproject.service.ProductService;
import lsn.example.testproject.service.impl.ProductServiceImpl;
import org.apache.tomcat.Jar;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import springfox.documentation.spring.web.json.Json;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
//@AutoConfigureWebMvc // 이 어노테이션을 통해 MockMvc를 builder 없이 주입받을수 있음
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //ProductController에서 잡고있는 bean 객체에 대해 mock 형태의 객체를 생성해줌
    @MockBean
    ProductServiceImpl productService;

    //http://localhost:8080/api/v1/product-api/product/{productId}
    @Test
    @DisplayName("프로덕트 데이터 가져오기 테스트")
    void getProductTest() throws Exception {

        //given : mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        // productService.getProduct() 함수를 실행할경우 productDTO를 리턴
        given(productService.getProduct("12315")).willReturn(
                new ProductDto("15871", "pen", 5000, 2000));

        String productId = "12315";

        //andExpect : 기대하는 값이 나왔는지 체크해 볼 수 있는 메소드
        // get()메소드를 수행한다. http://localhost:8080/api/v1/product-api/product/{productId} 이주소를 통해서

        mockMvc.perform(get("/api/v1/product-api/product/" + productId))
                //status 가 ok인지 확인
                .andExpect(status().isOk())
                //$.~~~ 를 통해서 제이슨형태의 바디값이 존재하는지 확인
                .andExpect(jsonPath("$.productId").exists()) // json path의 depth가 깊어지면 .을 추가하여 탐색할 수 있음 (ex : $.productId.productIdName)
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                // 테스트 내용 프린트
                .andDo(print());

        //verify : 해당 객체의 메소드가 실행되었는지 체크 -> productService의 getProduct()메소드가 실행되었는지 확인
        verify(productService,times(2)).getProduct("12315");
    }

    // http://localhost:8080/api/v1/product-api/product
    @Test
    @DisplayName("프로덕트 데이터 생성 테스트")
    void createProductTest() throws Exception {
        //mock 객체에서 특정 메소드가 실행되는 경우 실제 return을 줄 수 없기 때문에 아래와 같은 가정 사항을 만듦
        //given()을 통해서 saveProduct() 함수가 실행된다면 productDto를 반환
        given(productService.saveProduct("15871", "pen", 5000, 2000)).willReturn(
                new ProductDto("15871", "pen", 5000, 2000));

        //builder()를 통해서 id name price stock을 만듦
        ProductDto productDto = ProductDto.builder().productId("15871").productName("pen")
                .productPrice(5000).productStock(2000).build();

        // 구글에서 만들 json의 형태를 자유롭게 다룰수 있게 편의제공한 라이브러리
        Gson gson = new Gson();
        // productDTO값을 String 값으로 변경한값을 content에 담는다.
        String content = gson.toJson(productDto);

        // 아래 코드를 통해 json 형태 변경 작업을 대체할 수 있음
        //String json = new ObjectMapper().writeValueAsString(productDto);

        //post()를 수행
        mockMvc.perform(
                //post()를 할 경로를 설정
                post("/api/v1/product-api/product")
                        // json을 String값으로 만든 바디값을 넘겨줌
                        .content(content)
                        //content타입설정
                        .contentType(MediaType.APPLICATION_JSON))
                //돌아오는 값의 기대값 확인
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                //결과 출력
                .andDo(print());

        //saveProduct() 함수 실행되었는지 확인.
        verify(productService).saveProduct("15871", "pen", 5000, 2000);
    }

}
