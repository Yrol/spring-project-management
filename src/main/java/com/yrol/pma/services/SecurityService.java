package com.yrol.pma.services;

import com.yrol.pma.dao.SecurityRepository;
import com.yrol.pma.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    SecurityRepository securityRepository;
    public UserAccount save(UserAccount user) {
        return securityRepository.save(user);
    }

    public int userCount() {
        return securityRepository.findAll().size();
    }
}
