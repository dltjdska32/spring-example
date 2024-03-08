package lsn.example.testproject.data.repository;

import lsn.example.testproject.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository <ProductEntity(레퍼지토리가 사용할 Entity), String(프라이머리 키값)>
public interface ProductRepository extends JpaRepository <ProductEntity, String> {

}
