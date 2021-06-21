package com.example.router.service;

import com.example.router.model.Count;

import java.util.List;

public interface CountServiceInterface {
    void save(Count count);
    Count getById(long id);
    List<Count> getAllCount();
}
