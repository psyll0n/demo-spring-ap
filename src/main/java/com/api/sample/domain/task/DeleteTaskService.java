package com.api.sample.domain.task;

import com.api.sample.common.ErrorCodeEnum;
import com.api.sample.common.StatusCodeEnum;
import com.api.sample.exception.BusinessException;
import com.api.sample.model.response.StandardResponse;
import com.api.sample.service.BaseService;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteTaskService implements
    BaseService<Long, StandardResponse<Null>> {

  private final TaskRepository taskRepository;

  public DeleteTaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public StandardResponse<Null> execute(Long id) {
    Task task = taskRepository.findById(id).orElseThrow(() -> BusinessException.fromErrorCode(
        HttpStatus.NOT_FOUND,
        ErrorCodeEnum.ERR_1000_TASK_NOT_FOUND, null));
    taskRepository.delete(task);
    return StandardResponse.fromCodeEnum(StatusCodeEnum.STA_5000_SUCCESS);
  }
}
