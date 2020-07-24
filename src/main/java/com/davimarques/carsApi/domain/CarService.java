package com.davimarques.carsApi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;


/*
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
*/
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> getCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Iterable<Car> getCarByType(String type) {
        return carRepository.findByType(type);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car update(Car car, Long id) {

        Assert.notNull(id, "Não foi possível encontrar o id");

        Optional<Car> optional = getCarById(id);

        if(optional.isPresent()) {
            Car carDB = optional.get();
            carDB.setName(car.getName());
            carDB.setType(car.getType());

            carRepository.save(carDB);

            return carDB;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }

    }

    public void delete(Long id) {
        Optional<Car> car = getCarById(id);
        if(car.isPresent()) {
            carRepository.deleteById(id);
        }
    }
    
}
