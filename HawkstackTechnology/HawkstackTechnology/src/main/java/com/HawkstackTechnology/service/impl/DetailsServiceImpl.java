package com.HawkstackTechnology.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HawkstackTechnology.entity.Details;
import com.HawkstackTechnology.repository.DetailsRepository;
import com.HawkstackTechnology.service.DetailsService;

@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    private DetailsRepository repository;

    @Override
    public Details create(Details details) {
        return repository.save(details);
    }

    @Override
    public List<Details> getAll() {
        return repository.findAll();
    }

    @Override
    public Details update(Long id, Details details) {
        Details existing = repository.findById(id).orElseThrow();

        existing.setName(details.getName());
        existing.setEmail(details.getEmail());
        existing.setPhone(details.getPhone());
        existing.setAddress(details.getAddress());
        

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
