package ru.jobdream.service;

import org.springframework.stereotype.Service;
import ru.jobdream.model.City;
import ru.jobdream.persistence.CityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    private final CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public List<City> getAllCities() {
        List<City> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public City findById(int id) {
        return repository.findById(id).get();
    }
}
