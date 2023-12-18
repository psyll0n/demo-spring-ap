package com.api.sample.model.command;

import java.time.LocalDate;

public record FindAsPageQueryCommand(int pageNo, int pageSize, LocalDate from, LocalDate to) {

}
