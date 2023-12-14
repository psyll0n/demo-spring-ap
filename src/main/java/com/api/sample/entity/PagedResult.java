package com.api.sample.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PagedResult<T>(
    List<T> content,
    long totalElements,
    int pageNumber,
    int totalPages,
    @JsonProperty("isFirst") boolean isFirst,
    @JsonProperty("isLast") boolean isLast,
    @JsonProperty("hasNext") boolean hasNext,
    @JsonProperty("hasPrevious") boolean hasPrevious) {

}