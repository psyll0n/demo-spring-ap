package com.api.sample.model.response;

import java.time.Instant;

public record TaskDTO(
    Long id,
    String title,
    String description,
    Instant createdAt) {

}