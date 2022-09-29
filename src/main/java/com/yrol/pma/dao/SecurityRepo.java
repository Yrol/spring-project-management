package com.yrol.pma.dao;

import com.yrol.pma.entities.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityRepo extends CrudRepository<UserAccount, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM user_accounts WHERE email = :email OR username = :username")
    public List<UserAccount> getUsersByEmailOrUsername(@Param("email") String email, @Param("username") String username);
}
