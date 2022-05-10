package ru.jobdream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.jobdream.model.Post;
import ru.jobdream.persistence.PostRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ThreadSafe
@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Collection<Post> findAll() {
        List<Post> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public Post findById(int id) {
        return repository.findById(id).get();
    }

    public void create(Post post) {
        repository.save(post);
    }

    public void create(List<Post> posts) {
        repository.saveAll(posts);
    }

    public void update(Post post) {
        repository.updates(
                post.getName(),
                post.getDescription(),
                post.getCity().getId(),
                post.getId()
        );
    }

    public void delete(Post post) {
        repository.delete(post);
    }
}
