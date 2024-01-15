package com.folautech.icu.service;

import com.folautech.icu.dao.UserRepository;
import com.folautech.icu.model.User;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    private Faker faker = new Faker();

    private int numberOfUsers = 50;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i=1;i<=numberOfUsers;i++){
            String firstName = faker.name().firstName();

            if(i%2==0){
                firstName = "'"+firstName;
            }

            String lastName = faker.name().lastName();
            String email = (firstName+lastName).toLowerCase()+"@gmail.com";

            User savedUser = userRepository.saveAndFlush(User.builder()
                            .id(Long.parseLong(i+""))
                            .firstName(firstName)
                            .lastName(lastName)
                            .email(email)
                            .phoneNumber("")
                    .age((int)(Math.random() * 100))

                    .build());

            log.info("savedUser: {}", savedUser.toString());
        }

    }
}
