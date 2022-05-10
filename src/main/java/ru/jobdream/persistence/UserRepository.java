package ru.jobdream.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.jobdream.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query
    User findByEmailAndPassword(String email, String password);
}
