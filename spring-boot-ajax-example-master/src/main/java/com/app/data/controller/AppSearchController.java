package com.app.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.data.model.AjaxResponseBody;
import com.app.data.model.Customer;
import com.app.data.model.InvestigationCriteria;
import com.app.data.services.CustomerService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AppSearchController {

	CustomerService customerService;

	@Autowired
	public void setUserService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/api/search")
	public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody InvestigationCriteria search, Errors errors) {

		AjaxResponseBody result = new AjaxResponseBody();

		if (errors.hasErrors()) {

			result.setMsg(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			return ResponseEntity.badRequest().body(result);

		}

		List<Customer> customers = customerService.findByUserNameOrEmail(search.getUsername());
		if (customers.isEmpty()) {
			result.setMsg("no user found!");
		} else {
			result.setMsg("success");
		}
		result.setResult(customers);

		return ResponseEntity.ok(result);

	}

}
