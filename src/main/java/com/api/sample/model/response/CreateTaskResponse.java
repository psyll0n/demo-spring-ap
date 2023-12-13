package com.api.sample.model.response;

import java.time.Instant;

public record CreateTaskResponse(
    Long id,
    String title,
    String description,
    Instant createdAt) {

}