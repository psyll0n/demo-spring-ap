package com.api.sample.domain.task;

import com.api.sample.common.StatusCodeEnum;
import com.api.sample.model.request.CreateTaskRequest;
import com.api.sample.model.response.CreateTaskResponse;
import com.api.sample.model.response.StandardResponse;
import com.api.sample.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateTaskService implements
    BaseService<CreateTaskRequest, StandardResponse<CreateTaskResponse>> {

  private final TaskRepository taskRepository;

  public CreateTaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public StandardResponse<CreateTaskResponse> execute(CreateTaskRequest input) {
    Task task = new Task();
    task.setTitle(input.title());
    task.setDescription(input.description());

    Task newTask = taskRepository.save(task);
    CreateTaskResponse response = new CreateTaskResponse(newTask.getId(), newTask.getTitle(),
        newTask.getDescription(), newTask.getCreatedAt());
    return StandardResponse.fromCodeEnum(StatusCodeEnum.STA_5000_SUCCESS, response);
  }
}
