package com.gmail.deniska1406sme.sceduleservice.repository;

import com.gmail.deniska1406sme.sceduleservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUserId(Long userId);
}
