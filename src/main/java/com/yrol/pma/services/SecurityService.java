package com.yrol.pma.services;

import com.yrol.pma.dao.SecurityRepo;
import com.yrol.pma.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityService {

    @Autowired
    SecurityRepo securityRepo;

    public UserAccount save(UserAccount user) {
        return securityRepo.save(user);
    }

    public List<UserAccount> getUsersByEmailOrUsername(UserAccount user) {
        return securityRepo.getUsersByEmailOrUsername(user.getEmail(), user.getUserName());
    }
}
