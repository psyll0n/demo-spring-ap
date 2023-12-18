package com.api.sample.domain.task;

import com.api.sample.common.StatusCodeEnum;
import com.api.sample.entity.PagedResult;
import com.api.sample.model.command.FindAsPageQueryCommand;
import com.api.sample.model.response.StandardResponse;
import com.api.sample.model.response.TaskDTO;
import com.api.sample.service.BaseService;
import java.time.Instant;
import java.time.ZoneOffset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class GetTasksService implements
    BaseService<FindAsPageQueryCommand, StandardResponse<PagedResult<TaskDTO>>> {

  private final TaskRepository taskRepository;

  public GetTasksService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public StandardResponse<PagedResult<TaskDTO>> execute(
      FindAsPageQueryCommand queryCommand) {
    Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
    // From user POV, page number starts from 1, but for Spring Data JPA page number starts from 0.
    int pageNo = queryCommand.pageNo() > 0 ? queryCommand.pageNo() - 1 : 0;
    Pageable pageable = PageRequest.of(pageNo, queryCommand.pageSize(), sort);

    Instant from = queryCommand.from().atStartOfDay(ZoneOffset.UTC).toInstant();
    Instant to = queryCommand.to().atStartOfDay(ZoneOffset.UTC).toInstant();
    log.info("From: {} ~ To: {}", from, to);

    Page<TaskDTO> page = taskRepository.findTasks(pageable, from, to);
    var result = new PagedResult<>(
        page.getContent(),
        page.getTotalElements(),
        page.getNumber() + 1, // Page number starts from 1 for user
        page.getTotalPages(),
        page.isFirst(),
        page.isLast(),
        page.hasNext(),
        page.hasPrevious()
    );
    return StandardResponse.fromCodeEnum(StatusCodeEnum.STA_5000_SUCCESS, result);
  }
}
