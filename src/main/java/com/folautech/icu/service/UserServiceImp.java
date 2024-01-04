package com.folautech.icu.service;

import com.folautech.icu.dao.UserRepository;
import com.folautech.icu.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getUsers(Pageable pageable) {
        log.info("getUsers...");
        return userRepository.findAll(pageable);
    }
}
