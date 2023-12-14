package com.api.sample.domain.task;

import com.api.sample.common.StatusCodeEnum;
import com.api.sample.model.request.CreateTaskRequest;
import com.api.sample.model.response.TaskDTO;
import com.api.sample.model.response.StandardResponse;
import com.api.sample.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateTaskService implements
    BaseService<CreateTaskRequest, StandardResponse<TaskDTO>> {

  private final TaskRepository taskRepository;

  public CreateTaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public StandardResponse<TaskDTO> execute(CreateTaskRequest input) {
    Task task = new Task();
    task.setTitle(input.title());
    task.setDescription(input.description());

    Task newTask = taskRepository.save(task);
    TaskDTO response = new TaskDTO(newTask.getId(), newTask.getTitle(),
        newTask.getDescription(), newTask.getCreatedAt());
    return StandardResponse.fromCodeEnum(StatusCodeEnum.STA_5000_SUCCESS, response);
  }
}
