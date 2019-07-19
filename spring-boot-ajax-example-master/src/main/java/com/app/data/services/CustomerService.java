package com.app.data.services;

import org.springframework.stereotype.Service;

import com.app.data.model.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

	private List<Customer> customers;

	// Love Java 8
	public List<Customer> findByUserNameOrEmail(String username) {

		List<Customer> result = customers.stream().filter(x -> x.getUsername().equalsIgnoreCase(username))
				.collect(Collectors.toList());

		return result;

	}

	// Init some customers for testing
	@PostConstruct
	private void iniDataForTesting() {

		customers = new ArrayList<Customer>();

		Customer user1 = new Customer("Bhanu", "bhanu123", "bhanu@gmail.com");
		Customer user2 = new Customer("Krishana", "krishana222", "krishana@gmail.com");
		Customer user3 = new Customer("Uday", "uday333", "uday@gmail.com");

		customers.add(user1);
		customers.add(user2);
		customers.add(user3);

	}

}
