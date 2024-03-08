package lsn.example.testproject.data.dao.impl;

import lsn.example.testproject.data.dao.ProductDAO;
import lsn.example.testproject.data.entity.ProductEntity;
import lsn.example.testproject.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//클라이언트 -> 컨트롤러 -> 서비스 -> DAO(레퍼지토리) -> DB
//클라이언트 <- 컨트롤러 <- 서비스 <- DAO(레퍼지토리) <- DB
//클라이언트 -> 컨트롤러 -> 서비스, 클라이언트 <- 컨트롤러 <- 서비스 이부분에서 데이터이동은 DTO를 통해서 데이터 이동
//서비스 -> DAO(레퍼지토리) -> DB, 서비스 <- DAO(레퍼지토리) <- DB 이부분에서 데이터 이동은 Entity를 통해서 데이터 이동


//엔터티값을 데이터베이스에 저장하는 클래스
//실제 구현체
@Service
public class ProductDAOImpl implements ProductDAO {

 ProductRepository productRepository;

 @Autowired
 public ProductDAOImpl(ProductRepository productRepository) {
     this.productRepository = productRepository;
 }


 @Override
 public ProductEntity saveProduct(ProductEntity productEntity) {
     //데이터베이스에 저장
     productRepository.save(productEntity);
     return productEntity;
 }

 @Override
public ProductEntity getProduct(String productId) {
     //아이디를 기반으로 데이터를 가져옴
     ProductEntity productEntity = productRepository.getById(productId);
     return productEntity;
}



}
