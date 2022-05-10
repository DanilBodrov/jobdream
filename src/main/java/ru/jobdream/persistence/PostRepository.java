package ru.jobdream.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.jobdream.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Post p SET " +
            "p.name = :name, " +
            "p.description = :description, " +
            "p.city.id = :city_id " +
            "where p.id = :id")
    void updates(
            @Param("name") String name,
            @Param("description") String description,
            @Param("city_id") int city_id,
            @Param("id") int id
    );
}
