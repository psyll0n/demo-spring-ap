package com.api.sample.domain.task;

import com.api.sample.model.response.TaskDTO;
import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface TaskRepository extends JpaRepository<Task, Long> {

  @Query("""
         SELECT
          new com.api.sample.model.response.TaskDTO(t.id, t.title, t.description, t.createdAt)
         FROM Task t
      """)
  Page<TaskDTO> findTasks(Pageable pageable);

  @Query("""
         SELECT
          new com.api.sample.model.response.TaskDTO(t.id, t.title, t.description, t.createdAt)
         FROM Task t
         WHERE t.createdAt BETWEEN :from AND :to
      """)
  Page<TaskDTO> findTasks(Pageable pageable, Instant from, Instant to);
}
