package ru.jobdream.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.jobdream.model.Candidate;

import java.time.LocalDateTime;

public interface CandidateRepository extends CrudRepository<Candidate, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Candidate c SET " +
            "c.name = :name, " +
            "c.description = :description, " +
            "c.created = :created, " +
            "c.photo = :photo " +
            "where c.id = :id")
     void update(
            @Param("name") String name,
            @Param("description") String description,
            @Param("created") LocalDateTime created,
            @Param("photo") byte[] photo,
            @Param("id") int id
    );
}
