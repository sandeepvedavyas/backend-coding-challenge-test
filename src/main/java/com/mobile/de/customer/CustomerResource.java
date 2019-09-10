package com.mobile.de.customer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("customer")
public class CustomerResource {
	
	@Autowired
	private CustomerService customerService ;

	@GetMapping()
	public List<Customer> list() {
		return customerService.list();
	}

	@GetMapping("{id}")
	public Resource<Customer> get(@PathVariable long id) throws Exception {
		
		Resource<Customer> resource = new Resource<Customer>(customerService.get(id));
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).list());
		resource.add(linkTo.withRel("all-customers"));
		return resource;
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable long id) throws Exception {
		customerService.delete(id);
	}

	@PostMapping()
	public ResponseEntity<Object> create(@Valid @RequestBody Customer customer) {
		Customer persistedCustomer = customerService.create(customer);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(persistedCustomer.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody Customer customer, @PathVariable long id) throws Exception {

		Customer updatedCustomer = customerService.update(customer, id);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(updatedCustomer.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
