package com.nd.electronic.web.MTechDistributions.Repositories;

import com.nd.electronic.web.MTechDistributions.Entitys.CartEntity;
import com.nd.electronic.web.MTechDistributions.Entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<CartEntity,String> {

    public Optional<CartEntity> findByUserEntity(UserEntity userEntity);
}
