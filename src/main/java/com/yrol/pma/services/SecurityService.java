package com.yrol.pma.services;

import com.yrol.pma.dao.SecurityRepo;
import com.yrol.pma.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    SecurityRepo securityRepo;

    public UserAccount save(UserAccount user) {
        return securityRepo.save(user);
    }
}
