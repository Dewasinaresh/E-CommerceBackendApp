package com.nd.electronic.web.MTechDistributions.Repositories;

import com.nd.electronic.web.MTechDistributions.Entitys.CartItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItemsEntity,Integer> {

}
