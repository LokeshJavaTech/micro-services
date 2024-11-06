package com.lokesh.microservices.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

/*
We are selecting WebEnvironment = RANDOM_PORT, so that whenever we will run the integration test,
the test will spin-up the application, the default port every time will be 8080,
and we want to run the application on random port,
as it may conflict with the existing running processes.
*/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	// Because of this annotation, we don't have to specify mongo db uri (as we defined in application.properties)
	// It will automatically define mongodb host and port while running the application
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;		// Once application will spin-up, its port will be injected into this variable

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost"; // We are not providing port here because we are using RANDOM_PORT
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}
	@Test
	void shouldCreateProduct() {
		// Text-blocks
		String requestBody = """
								{
									"name": "iPhone 15",
									"description": "iPhone 15 is a latest launch in the market.",
									"price": 1000
								}
							""";
		RestAssured.given()								// GIVEN
				.contentType("application/json")
				.body(requestBody)
				.when()									// WHEN
				.post("/api/product")
				.then()									// THEN
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("iPhone 15"))
				.body("description", Matchers.equalTo("iPhone 15 is a latest launch in the market."))
				.body("price", Matchers.equalTo(1000));
	}

}
