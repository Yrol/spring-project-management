package com.yrol.pma.dao;

import com.yrol.pma.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface SecurityRepo extends CrudRepository<UserAccount, Long> {
}
