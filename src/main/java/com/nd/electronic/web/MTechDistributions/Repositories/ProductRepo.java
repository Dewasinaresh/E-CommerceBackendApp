package com.nd.electronic.web.MTechDistributions.Repositories;

import com.nd.electronic.web.MTechDistributions.Entitys.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity,String> {

    List<ProductEntity> findByproductTitleContaining(String title);
    Page<ProductEntity> findByliveTrue(Pageable page);



}
