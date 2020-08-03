package com.davimarques.carsApi.domain;

import com.davimarques.carsApi.domain.dto.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> getCars() {
        return carRepository.findAll().stream().map(CarDTO::create).collect(Collectors.toList());
    }

    public Optional<CarDTO> getCarById(Long id) {
        return carRepository.findById(id).map(CarDTO::create);
    }

    public List<CarDTO> getCarByType(String type) {
        return carRepository.findByType(type).stream().map(CarDTO::create).collect(Collectors.toList());
    }

    public CarDTO insertCar(Car car) {
        Assert.isNull(car.getId(), "Não foi possível inserir o carro");
        return CarDTO.create(carRepository.save(car));
    }
    public CarDTO update(Car car, Long id) {

        Assert.notNull(id, "Não foi possível encontrar o id");

        Optional<Car> optional = carRepository.findById(id);

        if (optional.isPresent()) {
            Car carDB = optional.get();
            carDB.setName(car.getName());
            carDB.setType(car.getType());

            carRepository.save(carDB);

            return CarDTO.create(carDB);
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }

    }

    public boolean delete(Long id) {
        if (getCarById(id).isPresent()) {
            carRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
