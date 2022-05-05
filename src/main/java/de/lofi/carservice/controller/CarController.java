package de.lofi.carservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.lofi.carservice.dao.CarService;
import de.lofi.carservice.model.Car;

/**
 * Rest controller for car
 * 
 * @author lofi
 */

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})
public class CarController {

	public static final Logger LOG = LoggerFactory.getLogger(CarController.class);

	@Autowired
	private CarService carService;

	/**
	 * Gets all cars with request method GET
	 * 
	 * @return car list
	 */
	@GetMapping("/cars")
	public ResponseEntity<List<Car>> getCars() {
		return ResponseEntity.ok().body(carService.findAll());
	}

	/**
	 * Creates a new car with request method POST
	 * 
	 * @param car
	 * @return the new car
	 */
	@PostMapping("/cars/create")
	public ResponseEntity<Car> createCar(@RequestBody @Valid Car car) {
			return ResponseEntity.ok().body(carService.saveCar(car));
	}

	/**
	 * Finds a car by id
	 * 
	 * @param id
	 * @return car
	 */
	@GetMapping("/cars/read/{id}")
	public ResponseEntity<Car> readCar(@PathVariable Long id) {
		return ResponseEntity.ok().body(carService.find(id));
	}

	/**
	 * Updates a car with request method PUT
	 * 
	 * @param updatedcar
	 * @param id
	 * @return updated car
	 */
	@PutMapping("/cars/update/{id}")
	public ResponseEntity<Car> updateCar(@RequestBody Car toUpdateCar, @PathVariable Long id) {
		toUpdateCar.setId(id);
		return ResponseEntity.ok().body(carService.update(toUpdateCar));
	}

	/**
	 * Deletes a car by id with request method DELETE
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/cars/delete/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
		carService.deleteCar(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
