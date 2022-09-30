package com.yrol.pma.dao;

import com.yrol.pma.entities.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityRepository extends CrudRepository<UserAccount, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM user_accounts WHERE username = :username")
    public List<UserAccount> getUsersByUsername(@Param("username") String username);

    @Query(nativeQuery = true, value = "SELECT * FROM user_accounts WHERE email = :email")
    public  List<UserAccount> getUserByEmail(@Param("email") String email);
}
