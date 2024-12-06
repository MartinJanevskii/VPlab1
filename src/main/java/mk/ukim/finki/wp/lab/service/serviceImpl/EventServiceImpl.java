package mk.ukim.finki.wp.lab.service.serviceImpl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    final EventRepository er;
    final LocationRepository lr;

    public EventServiceImpl(EventRepository er,LocationRepository lr) {
        this.er = er;
        this.lr = lr;
    }

    @Override
    public List<Event> listAll() {
        return er.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, Double rating) {
        return er.findAllByNameAndPopularityScore(text,rating);
    }


    @Override
    public Optional<Event> save(String name, String description, Double popularity, Long id) {
        Location location = lr.findById(id).orElseThrow(RuntimeException::new);
        return Optional.of(er.save(new Event(name, description,popularity,location)));
    }

    @Override
    public Optional<Event> findById(Long id) {
        return er.findById(id);
    }

    @Override
    public Optional<Event> update(Long id, String name, String description, Double popularityScore, Long locationId) {
           Event event = er.findById(id).orElseThrow(IllegalArgumentException::new);
           Location location = lr.findById(locationId).orElseThrow(RuntimeException::new);
           event.setName(name);
           event.setName(name);
           event.setDescription(description);
           event.setPopularityScore(popularityScore);
           event.setLocation(location);
        return Optional.of(er.save(event));
    }

    @Override
    public void delete(Long id) {
        er.deleteById(id);
    }

    @Override
    public Optional<Event> increasePopularity(Long id) {
        Event event = er.findById(id).orElseThrow(IllegalArgumentException::new);
        double newPop = event.getPopularityScore()+0.5;
        event.setPopularityScore(newPop);
        event.setLiked(true);
        return Optional.of(er.save(event));
    }

    @Override
    public List<Event> findByLocId(Long id) {
        Location location = lr.findById(id).orElseThrow(IllegalArgumentException::new);
        return er.findAll().stream().filter(x->x.getLocation()==location).toList();
    }

}

