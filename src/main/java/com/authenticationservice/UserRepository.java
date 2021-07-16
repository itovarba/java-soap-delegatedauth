package com.authenticationservice;

import javax.annotation.PostConstruct;

import com.sforce.soap.authentication.Authenticate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserRepository {
	private static final Map<String, Authenticate> users = new HashMap<>();

	@PostConstruct
	public void initData() {
        Authenticate user = new Authenticate();
		user.setUsername("user");
		user.setPassword("user");
	
		users.put(user.getUsername(), user);

		Authenticate admin = new Authenticate();
		admin.setUsername("admin");
		admin.setPassword("admin");
	
		users.put(admin.getUsername(), admin);

        Authenticate iosu = new Authenticate();
		iosu.setUsername("iosu");
		iosu.setPassword("iosu");
	
		users.put(iosu.getUsername(), iosu);
	}

	public Authenticate findUser(String name) {
		Assert.notNull(name, "The users's name must not be null");
		return users.get(name);
	}
}