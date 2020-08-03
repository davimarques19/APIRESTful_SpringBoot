package com.davimarques.carsApi.controller;

import com.davimarques.carsApi.domain.Car;
import com.davimarques.carsApi.domain.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public ResponseEntity<Iterable<Car>> getAll() {

        return ResponseEntity.ok(carService.getCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCarById(@PathVariable("id") Long id) {

        Optional<Car> car = carService.getCarById(id);

        return car.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity getCarsByType(@PathVariable("type") String type) {
        List<Car> carsType = carService.getCarByType(type);

        return carsType.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carsType);
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
