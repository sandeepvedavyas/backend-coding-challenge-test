package com.mobile.de.ad;

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
@RequestMapping("/customer/{customerId}/ad")
public class AdResource {
    
	@Autowired
	private AdService adService ;

	@GetMapping()
	public List<Ad> list(@PathVariable long customerId) {
		return adService.list(customerId);
	}

	@GetMapping("{id}")
	public Resource<Ad> get(@PathVariable long customerId, @PathVariable long id) throws Exception {
		
		Resource<Ad> resource = new Resource<Ad>(adService.get(customerId,id));
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).list(customerId));
		resource.add(linkTo.withRel("all-adds-by-customer"));
		return resource;
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable long customerId, @PathVariable long id){
		
		adService.delete(customerId,id);
	}

	@PostMapping()
	public ResponseEntity<Object> create(@Valid @RequestBody Ad ad, @PathVariable long customerId) {
		
		Ad persistedAd = adService.create(ad,customerId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(persistedAd.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody Ad ad, @PathVariable long customerId, 
			@PathVariable long id){

		Ad updatedAd = adService.update(ad, customerId, id);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(updatedAd.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
