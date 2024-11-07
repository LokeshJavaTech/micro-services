package com.lokesh.microservices.inventory;

import com.lokesh.microservices.inventory.dto.InventoryResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldCheckIsInStock() {
		InventoryResponse actualResponse1 = RestAssured
				.given()								// GIVEN
				.when()									// WHEN
				.get("/api/inventory?skuCode=samsung_15g&quantity=50")
				.then()									// THEN
				.statusCode(200)
				.extract()
				.response()
				.as(InventoryResponse.class);
		Assertions.assertTrue(actualResponse1.isInStock());

		InventoryResponse actualResponse2 = RestAssured
				.given()								// GIVEN
				.when()									// WHEN
				.get("/api/inventory?skuCode=samsung_15g&quantity=150")
				.then()									// THEN
				.statusCode(200)
				.extract()
				.response()
				.as(InventoryResponse.class);
		Assertions.assertFalse(actualResponse2.isInStock());
	}

}