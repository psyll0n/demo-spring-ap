package com.api.sample.domain.task;

import com.api.sample.common.StatusCodeEnum;
import com.api.sample.exception.TaskNotFoundException;
import com.api.sample.model.command.UpdateTaskCommand;
import com.api.sample.model.response.StandardResponse;
import com.api.sample.service.BaseService;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateTaskService implements
    BaseService<UpdateTaskCommand, StandardResponse<Null>> {

  private final TaskRepository taskRepository;

  public UpdateTaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public StandardResponse<Null> execute(UpdateTaskCommand input) {
    Task task = taskRepository.findById(input.id())
        .orElseThrow(() -> TaskNotFoundException.of(input.id()));
    task.setTitle(input.title());
    task.setDescription(input.description());
    taskRepository.save(task);
    return StandardResponse.fromCodeEnum(StatusCodeEnum.STA_5000_SUCCESS);
  }
}
