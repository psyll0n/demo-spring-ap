package com.api.sample.model.command;

public record CreateTaskCommand(
    String title,
    String description) {

}