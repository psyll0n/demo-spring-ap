package com.api.sample.controller;

import com.api.sample.domain.task.CreateTaskService;
import com.api.sample.domain.task.GetTasksService;
import com.api.sample.domain.task.UpdateTaskService;
import com.api.sample.entity.PagedResult;
import com.api.sample.model.command.FindAsPageQueryCommand;
import com.api.sample.model.command.UpdateTaskCommand;
import com.api.sample.model.request.CreateTaskRequest;
import com.api.sample.model.request.UpdateTaskRequest;
import com.api.sample.model.response.TaskDTO;
import com.api.sample.model.response.StandardResponse;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final CreateTaskService createTaskService;
  private final UpdateTaskService updateTaskService;
  private final GetTasksService getTasksService;

  public TaskController(CreateTaskService createTaskService, UpdateTaskService updateTaskService,
      GetTasksService getTasksService) {
    this.createTaskService = createTaskService;
    this.updateTaskService = updateTaskService;
    this.getTasksService = getTasksService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  StandardResponse<TaskDTO> createTask(
      @RequestBody @Validated CreateTaskRequest input) {
    return createTaskService.execute(input);
  }

  @PutMapping("/{id}")
  StandardResponse<Null> updateTask(@PathVariable("id") Long id,
      @RequestBody @Validated UpdateTaskRequest request) {
    var updateTaskCommand = new UpdateTaskCommand(id, request.title(),
        request.description());
    return updateTaskService.execute(updateTaskCommand);
  }

  @GetMapping
  StandardResponse<PagedResult<TaskDTO>> getTasks(
      @RequestParam(name = "page", defaultValue = "1") Integer pageNo,
      @RequestParam(name = "size", defaultValue = "10") Integer pageSize) {
    var findAsPageQueryCommand = new FindAsPageQueryCommand(pageNo, pageSize);
    return getTasksService.execute(findAsPageQueryCommand);
  }
}
