package de.lofi.carservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.lofi.carservice.model.Car;

/**
 * Repository for car
 * 
 * @author lofi
 *
 */

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	public Optional<Car> findByFin(String fin);
	
	public Optional<Car> findByIdNotAndFin(Long id, String fin);
	
	public Optional<Car> findBySign(String sign);
	
	public Optional<Car> findByIdNotAndSign(Long id, String sign);

	public boolean existsByFin(String fin);

	public boolean existsBySign(String sign);
		
}
