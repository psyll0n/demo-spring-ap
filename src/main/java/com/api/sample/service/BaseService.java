package com.api.sample.service;

public interface BaseService<I, O> {

  O execute(I input);
}
