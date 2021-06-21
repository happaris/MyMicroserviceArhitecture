package com.example.router.service;

import com.example.router.model.Count;
import com.example.router.repository.CountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountService implements CountServiceInterface {

    private CountRepository repository;

    @Autowired
    public CountService(CountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Count count) {
        repository.save(count);
    }

    @Override
    public Count getById(long id) {
        return repository.getById(id);
    }
    @Override
    public List<Count> getAllCount() {
        return repository.findAll();
    }
}
