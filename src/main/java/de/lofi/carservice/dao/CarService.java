package de.lofi.carservice.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.lofi.carservice.exception.CarAlreadyExistingException;
import de.lofi.carservice.exception.CarNotFoundException;
import de.lofi.carservice.model.Car;

/**
 * Service for car
 * 
 * @author lofi
 *
 */

@Service
public class CarService {

	private static final Logger LOG = LoggerFactory.getLogger(CarService.class);

	@Autowired
	private CarRepository carRepository;

	/**
	 * lists all cars
	 * 
	 * @return cars
	 */
	public List<Car> findAll() {
		LOG.debug("list of all cars");
		return carRepository.findAll();
	}

	/**
	 * Creates a car throws exception, if the car is already existing or the
	 * to create car has the same informations like the existing car
	 * 
	 * @param car
	 * @return new car
	 */
	@Transactional
	public Car saveCar(Car car) {
		if (car.getId() != null && carRepository.existsById(car.getId())
				|| carRepository.findAll(Example.of(car)).size() > 0) {
			LOG.error("{} already existing", car);
			throw new CarAlreadyExistingException("Das Fahrzeug ist bereits vorhanden");
		}
		
		if (carRepository.existsBySign(car.getSign())) {
			LOG.error("Sign {} already existing", car.getSign());
			throw new CarAlreadyExistingException("Das Kennzeichen ist bereits vorhanden");
		}
		
		if (carRepository.existsByFin(car.getFin())) {
			LOG.error("{} already existing", car.getFin());
			throw new CarAlreadyExistingException("Die Fin ist bereits vorhanden");
		}
		car = carRepository.save(car);
		LOG.info("{} is successfully created", car);
		return car;
	}

	/**
	 * Deletes a car throws exception, if the car is not found
	 * 
	 * @param id
	 *
	 */
	@Transactional
	public boolean deleteCar(Long id) {
		if (id!= null && !carRepository.existsById(id)) {
			LOG.error("car id {} not found", id);
			throw new CarNotFoundException("Das Fahrzeug ist nicht vorhanden");
		}
		carRepository.deleteById(id);
		LOG.info("car id {} is successfully deleted ", id);
		return true;
	}

	/**
	 * Updates a car throws exception, if the car is not found
	 * 
	 * @param car
	 * @return updated car
	 */
	@Transactional
	public Car update(Car car) {
		if (car.getId() != null && !carRepository.existsById(car.getId())) {
			LOG.error("{} already existing", car);
			throw new CarNotFoundException("Das Fahrzeug ist nicht vorhanden");
		}
		
		if (carRepository.findByIdNotAndSign(car.getId(), car.getSign()).isPresent()) {
			LOG.error("Sign {} already existing", car.getSign());
			throw new CarAlreadyExistingException("Das Kennzeichen ist bereits vorhanden");
		}
			
		if (carRepository.findByIdNotAndFin(car.getId(), car.getFin()).isPresent()) {
			LOG.error("Fin {} already existing", car.getFin());
			throw new CarAlreadyExistingException("Die FIN ist bereits vorhanden");
		}
		Car updatedCar = carRepository.save(car);
		LOG.info("{} is successfully updated", car);
		return updatedCar;
	}

	/**
	 * Finds a car by id throws exception, if the car is not found
	 * 
	 * @param id
	 * @return found car
	 */
	public Car find(Long id) {
		if (!carRepository.existsById(id)) {
			throw new CarNotFoundException("Das Fahrzeug ist nicht vorhanden");
		}
		return carRepository.findById(id).get();
	}

	/**
	 * Finds a car by fin throws exception, if the car is not found
	 * 
	 * @param id
	 * @return found car
	 */
	public Car findByFin(String fin) {
		if (!carRepository.existsByFin(fin)) {
			throw new CarAlreadyExistingException(fin);
		}
		return carRepository.findByFin(fin).get();
	}
	
}
