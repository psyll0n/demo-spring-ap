package com.api.sample.controller;

import com.api.sample.domain.task.CreateTaskService;
import com.api.sample.model.request.CreateTaskRequest;
import com.api.sample.model.response.CreateTaskResponse;
import com.api.sample.model.response.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final CreateTaskService createTaskService;

  public TaskController(CreateTaskService createTaskService) {
    this.createTaskService = createTaskService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public StandardResponse<CreateTaskResponse> createTask(
      @RequestBody @Validated CreateTaskRequest input) {
    return createTaskService.execute(input);
  }
}
