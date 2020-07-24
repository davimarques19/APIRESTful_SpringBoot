package com.davimarques.carsApi.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> getCars() {
        return carRepository.findAll();
    }

}
