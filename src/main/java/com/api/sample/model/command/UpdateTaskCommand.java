package com.api.sample.model.command;

public record UpdateTaskCommand(
    Long id,
    String title,
    String description) {

}