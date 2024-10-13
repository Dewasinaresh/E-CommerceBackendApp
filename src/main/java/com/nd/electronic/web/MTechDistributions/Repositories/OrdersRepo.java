package com.nd.electronic.web.MTechDistributions.Repositories;

import com.nd.electronic.web.MTechDistributions.DTOS.UserDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.OrdersEntity;
import com.nd.electronic.web.MTechDistributions.Entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity,String> {
   public List<OrdersEntity> findByUserEntity(UserEntity userEntity);
}
