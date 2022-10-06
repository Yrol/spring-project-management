package com.yrol.pma.dao;

import com.yrol.pma.entities.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityRepository extends CrudRepository<UserAccount, Long> {

    /**
     * Overriding the "findAll()" of CrudRepository to return a List instead of type Iterable
     * */
    @Override
    public List<UserAccount> findAll();

    /**
     * Method 1: find users with existing username or email by querying manually
     * */
//    @Query(nativeQuery = true, value = "SELECT * FROM user_accounts WHERE username = :username")
//    public List<UserAccount> getUsersByUsername(@Param("username") String username);
//
//    @Query(nativeQuery = true, value = "SELECT * FROM user_accounts WHERE email = :email")
//    public  List<UserAccount> getUserByEmail(@Param("email") String email);

    /**
     * Method 2: find users with existing username or email by automatic queries using exact attribute name along with "findBy" - findBy<Attribute>
     * */
    public List<UserAccount> findByUserName(String username);

    public  List<UserAccount> findByEmail(String email);
}
