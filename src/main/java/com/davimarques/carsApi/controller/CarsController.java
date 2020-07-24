package com.davimarques.carsApi.controller;

import com.davimarques.carsApi.domain.Car;
import com.davimarques.carsApi.domain.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public Iterable<Car> getAll() {
        return carService.getCars();
    }

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable("id") Long id) {
        return carService.getCarById(id);
    }

    @GetMapping("/type/{type}")
    public Iterable<Car> getCarByType(@PathVariable("type") String type) {
        return carService.getCarByType(type);
    }

    @PostMapping
    public void insertCar(@RequestBody Car car) {
        carService.saveCar(car);
    }

    @PutMapping("/{id}")
    public void updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        carService.update(car, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long id){
        carService.delete(id);
    }

}
