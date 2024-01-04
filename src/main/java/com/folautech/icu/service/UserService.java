package com.folautech.icu.service;

import com.folautech.icu.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<User> getUsers(Pageable pageable);
}
