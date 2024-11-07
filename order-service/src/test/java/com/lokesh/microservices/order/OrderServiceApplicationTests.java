package com.lokesh.microservices.order;

import com.lokesh.microservices.order.dto.OrderResponse;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

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
	void shouldSubmitOrder() {
		String submitOrderJson = """
                {
                     "skuCode": "iphone_15",
                     "price": 1000,
                     "quantity": 1
                }
                """;

		OrderResponse actualResponse = RestAssured
				.given()								// GIVEN
				.contentType("application/json")
				.body(submitOrderJson)
				.when()									// WHEN
				.post("/api/order")
				.then()									// THEN
				.statusCode(201)
				.extract()
				.body()
				.as(OrderResponse.class);

		MatcherAssert.assertThat(actualResponse.skuCode(), Matchers.equalTo("iphone_15"));
		MatcherAssert.assertThat(actualResponse.price(), Matchers.equalTo(new BigDecimal(1000)));
		MatcherAssert.assertThat(actualResponse.quantity(), Matchers.equalTo(1));
	}

}