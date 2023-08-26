package com.helpe.YoHelper.repository;

import com.helpe.YoHelper.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>  {
    boolean existsByMail(String mail);
}
