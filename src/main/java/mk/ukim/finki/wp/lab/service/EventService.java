package mk.ukim.finki.wp.lab.service;


import mk.ukim.finki.wp.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text, Double rating);

    Optional<Event> save(String name, String description, Double popularity, Long id);
    Optional<Event> findById(Long id);
    Optional<Event>update(Long id, String name, String description, Double popularityScore, Long locationId);
    void delete(Long id);
    Optional<Event> increasePopularity(Long id);

    List<Event> findByLocId(Long id);
}


