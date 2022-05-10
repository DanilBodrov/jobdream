package ru.jobdream.persistence;

import org.springframework.data.repository.CrudRepository;
import ru.jobdream.model.City;

public interface CityRepository extends CrudRepository<City, Integer> {
}
