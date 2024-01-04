package com.folautech.icu;

import com.folautech.icu.dao.UserRepository;
import com.folautech.icu.model.User;
import com.folautech.icu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class SpringbootWithIcuApplicationTests {


	@Autowired
	private UserService userService;

	@Test
	void getSortedUsers() {

		Pageable pageable = PageRequest.of(0,20, Sort.by(Sort.Order.desc("firstName")));

		Page<User> page = userService.getUsers(pageable);

		List<User> users = page.getContent();

		for(User user : users){
			System.out.println(user.getFirstName());
		}

	}

}
