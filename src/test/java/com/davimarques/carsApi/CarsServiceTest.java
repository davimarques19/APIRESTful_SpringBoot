package com.davimarques.carsApi;

import com.davimarques.carsApi.domain.Car;
import com.davimarques.carsApi.domain.CarService;
import com.davimarques.carsApi.domain.dto.CarDTO;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarsServiceTest {

	@Autowired
	private CarService service;

	@Test
	public void testSave() {

		Car car = new Car();
		car.setName("Porshe");
		car.setType("esportivos");

		CarDTO c = service.insertCar(car);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		/* Buscar o objeto
		c = service.getCarById(id);
		assertNotNull(c);
		assertEquals("Porshe",c.getName());
		assertEquals("esportivos",c.getType());


		// Deletar o objeto
		service.delete(id);

		// Verificar se deletou
		try {
			service.getCarById(id);
			fail("O carro não foi excluído");
		} catch (ObjectNotFoundException e) {
			// OK
		}
		 */
	}

	@Test
	public void CarsList() {

		List<CarDTO> carros = service.getCars();

		assertEquals(30, carros.size());
	}

	@Test
	public void testListaPorTipo() {

		assertEquals(10, service.getCarByType("classicos").size());
		assertEquals(10, service.getCarByType("esportivos").size());
		assertEquals(10, service.getCarByType("luxo").size());

		assertEquals(0, service.getCarByType("x").size());
	}

	@Test
	public void getCarByIdTest() {

		//CarDTO c = service.getCarById(11L);

		//assertNotNull(c);

		//assertEquals("Ferrari FF", c.getName());
	}
}
