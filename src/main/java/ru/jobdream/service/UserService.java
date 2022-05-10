package ru.jobdream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.jobdream.model.User;
import ru.jobdream.persistence.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ThreadSafe
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findUserByEmailAndPwd(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    public void create(User user) {
        repository.save(user);
    }

    public Collection<User> findAll() {
        List<User> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }
}
