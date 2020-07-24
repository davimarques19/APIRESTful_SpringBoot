package com.davimarques.carsApi.controller;

import com.davimarques.carsApi.domain.Car;
import com.davimarques.carsApi.domain.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public Iterable<Car> getAll() {
        return carService.getCars();
    }

}
