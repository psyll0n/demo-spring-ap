package com.api.sample.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.api.sample.domain.task.CreateTaskService;
import com.api.sample.model.request.CreateTaskRequest;
import com.api.sample.model.response.TaskDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
public class TaskControllerTests {

  @Container
  @ServiceConnection
  static MySQLContainer<?> mysqlContainer = new MySQLContainer<>(
      DockerImageName.parse("mysql:latest"));
  @LocalServerPort
  private Integer port;

  @Autowired
  private CreateTaskService createTaskService;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  @Sql("/test-data.sql")
  void shouldGetTasksByPage() {
    given().contentType(ContentType.JSON)
        .when()
        .get("/sample/tasks?page=1&size=10")
        .then()
        .statusCode(200)
        .body("statusCode", equalTo("5000"))
        .body("message", equalTo("Request successfully processed"))
        .body("data.content.size()", equalTo(2))
        .body("data.totalElements", equalTo(2))
        .body("data.pageNumber", equalTo(1))
        .body("data.totalPages", equalTo(1))
        .body("data.isFirst", equalTo(true))
        .body("data.isLast", equalTo(true))
        .body("data.hasNext", equalTo(false))
        .body("data.hasPrevious", equalTo(false));
  }

  @Test
  void shouldCreateTaskSuccessfully() {
    given().contentType(ContentType.JSON)
        .body(
            """
                {
                "title": "Test",
                "description": "Testing"
                }
                """
        )
        .when()
        .post("/sample/tasks")
        .then()
        .statusCode(201)
        .body("statusCode", equalTo("5000"))
        .body("message", equalTo("Request successfully processed"))
        .body("data.id", notNullValue())
        .body("data.title", equalTo("Test"))
        .body("data.description", equalTo("Testing"))
        .body("data.createdAt", notNullValue())
        .body("data.updatedAt", nullValue());
  }

  @Test
  void shouldUpdateTaskSuccessfully() {
    // Create a task
    CreateTaskRequest request = new CreateTaskRequest("Test", "Testing");
    TaskDTO taskDTO = createTaskService.execute(request).getData();

    given().contentType(ContentType.JSON)
        .body(
            """
                {
                "title": "Test Updated",
                "description": "Updating"
                }
                """
        )
        .when()
        .put("/sample/tasks/{id}", taskDTO.id())
        .then()
        .statusCode(200)
        .body("statusCode", equalTo("5000"))
        .body("message", equalTo("Request successfully processed"));
  }
}
