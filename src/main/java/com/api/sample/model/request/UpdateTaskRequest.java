package com.api.sample.model.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateTaskRequest(
    @NotBlank(message = "Title is required")
    String title,
    String description) {

}