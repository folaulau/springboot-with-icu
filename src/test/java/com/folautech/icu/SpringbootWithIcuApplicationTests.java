package com.folautech.icu;

import com.folautech.icu.dao.UserRepository;
import com.folautech.icu.model.User;
import com.folautech.icu.service.UserService;
import com.ibm.icu.text.Collator;
import com.ibm.icu.text.RuleBasedCollator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@SpringBootTest
class SpringbootWithIcuApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void getSortedUsers() throws Exception {

		//Sort.by(Sort.Order.desc("firstName"))
        Pageable pageable = PageRequest.of(0, 20);

        Page<User> page = userService.getUsers(pageable);


        RuleBasedCollator collator = (RuleBasedCollator) Collator.getInstance(Locale.US); // Swedish locale
        collator.setStrength(Collator.QUATERNARY);
        collator.setAlternateHandlingShifted(true);

        List<User> users = page.getContent();

        for (User user : users) {
            System.out.println(user.getFirstName());

            String collationKeyToString = new String(collator.getCollationKey(user.getAge()+"").toByteArray(), StandardCharsets.UTF_16BE);

            System.out.println("collationKeyToString: "+collationKeyToString);
        }

        List<User> copiedUsers = new ArrayList<>();
        for (User obj : users) {
            copiedUsers.add(obj); // Assuming MyObject has a copy constructor

        }

		System.out.println("=================");

		Collections.sort(copiedUsers, (User u1, User u2) -> collator.compare(u1.getFirstName(), u2.getFirstName()));

        for (User user : copiedUsers) {
            System.out.println(user.getFirstName());
        }
    }

}
