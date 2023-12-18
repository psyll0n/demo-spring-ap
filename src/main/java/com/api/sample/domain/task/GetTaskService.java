package com.api.sample.domain.task;

import com.api.sample.common.ErrorCodeEnum;
import com.api.sample.common.StatusCodeEnum;
import com.api.sample.entity.PagedResult;
import com.api.sample.exception.BusinessException;
import com.api.sample.model.command.FindAsPageQueryCommand;
import com.api.sample.model.response.StandardResponse;
import com.api.sample.model.response.TaskDTO;
import com.api.sample.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetTaskService implements
    BaseService<Long, StandardResponse<TaskDTO>> {

  private final TaskRepository taskRepository;

  public GetTaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public StandardResponse<TaskDTO> execute(Long id) {
    Task task = taskRepository.findById(id).orElseThrow(() -> BusinessException.fromErrorCode(
        HttpStatus.NOT_FOUND,
        ErrorCodeEnum.ERR_1000_TASK_NOT_FOUND, null));
    TaskDTO taskDTO = new TaskDTO(task.getId(), task.getTitle(),
        task.getDescription(), task.getCreatedAt());
    return StandardResponse.fromCodeEnum(StatusCodeEnum.STA_5000_SUCCESS, taskDTO);
  }
}
