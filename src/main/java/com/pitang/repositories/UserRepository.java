package com.pitang.repositories;
import com.pitang.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Override
    @NotNull
    UserModel getById(@NotNull Long aLong);

    UserModel findByEmail(String email);
}