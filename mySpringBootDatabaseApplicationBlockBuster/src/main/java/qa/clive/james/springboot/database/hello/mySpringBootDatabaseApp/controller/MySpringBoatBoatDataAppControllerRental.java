package qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.model.MySpringBootDataModelRental;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository.MySpringBootRepositoryPerson;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository.MySpringBootRepositoryRental;
import qa.james.gareth.springboot.database.hello.mySpringBootDatabaseApp.exception.ResourceNotFoundException;

@RestController
public class MySpringBoatBoatDataAppControllerRental {
	
	@Autowired
	private MySpringBootRepositoryRental repositoryRental;
	
	@Autowired
	private MySpringBootRepositoryPerson repositoryPerson;
	
	
	@GetMapping("/person/{personId}/rental")
	public Page<MySpringBootDataModelRental> getAllRentalsByPersonId(@PathVariable (value = "personId") Long personId, Pageable pageable){
		return repositoryRental.findByPersonId(personId, pageable);
	}
	
	@PostMapping("/person/{personId}/orders")	
	public MySpringBootDataModelRental creatComment(@PathVariable (value = "personId") Long personId, @Valid @RequestBody MySpringBootDataModelRental rental) {
		return repositoryPerson.findById(personId).map(mySpringBootDataModelPerson -> {
			rental.setPersonId(mySpringBootDataModelPerson);
			return repositoryRental.save(rental);
		}).orElseThrow(() -> new ResourceNotFoundException("Person","id",rental));
	}
	
	@PutMapping("/person/{personId}/rental/{rentalId}")
	public MySpringBootDataModelRental updateOrder(@PathVariable (value = "personId") Long personId, @PathVariable (value = "orderId") Long rentalId, @Valid @RequestBody MySpringBootDataModelRental rental) {
		if(!repositoryPerson.existsById(personId)) {
			throw new ResourceNotFoundException("Person","id",rental);
		}
		return repositoryRental.findById(rentalId).map(rental -> {
			rental.setRentalDuration(rental.getRentalDuration());
			return repositoryRental
		})
	}
	
	
	
	
}
