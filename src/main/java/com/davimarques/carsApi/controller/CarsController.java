package com.davimarques.carsApi.controller;

import com.davimarques.carsApi.domain.Car;
import com.davimarques.carsApi.domain.CarService;
import com.davimarques.carsApi.domain.dto.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public ResponseEntity getAll() {

        return ResponseEntity.ok(carService.getCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCarById(@PathVariable("id") Long id) {
        return carService.getCarById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity getCarsByType(@PathVariable("type") String type) {
        List<CarDTO> carsType = carService.getCarByType(type);

        return carsType.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carsType);
    }

    @PostMapping
    public ResponseEntity insertCar(@RequestBody Car car) {

        try {
            CarDTO c = carService.insertCar(car);

            URI location = getURI(c.getId());
            return ResponseEntity.created(location).build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getURI(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        return carService.update(car, id) != null ?

                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable("id") Long id){

        return  carService.delete(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

}
