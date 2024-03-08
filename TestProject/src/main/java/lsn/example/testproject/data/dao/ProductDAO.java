package lsn.example.testproject.data.dao;

import lsn.example.testproject.data.entity.ProductEntity;

// 인터페이스를 사용하는 이유는 만약 다른 DB를 사용할경우 교체를 편하게 하기위해서
public interface ProductDAO {

    ProductEntity saveProduct(ProductEntity productEntity);

    ProductEntity getProduct(String productId);
}
