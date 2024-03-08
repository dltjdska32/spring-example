package lsn.example.testproject.service;

import lsn.example.testproject.data.dto.ProductDto;



public interface ProductService {
    //데이터베이스에 저장
    ProductDto saveProduct(String productId, String productName, int productPrice, int productStock);
    //조회기능
    ProductDto getProduct(String productId);
}
