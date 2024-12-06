package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    static List<Event> events;
    static List<Location> locationList;

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public DataHolder(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        events = null;
        locationList = null;
    }

    @PostConstruct
    public void init(){
        events = new ArrayList<>();
        locationList = new ArrayList<>();
        locationList.add(new Location("Akron", "Ohio","180000", "LeBron's hometown"));
        locationList.add(new Location("Los Angeles","Blagoja Toska 11","150000","Teteks homeground"));
        locationList.add(new Location("Skopje", "Partizanska","500000", "Finki, feit, tmf"));
        locationList.add(new Location("Kumanovo","Petachko vodiche 21","70000","The city of sport"));
        locationList.add(new Location("Ohrid", "ASNOM 7","310000", "Vacation home for families"));
        this.locationRepository.saveAll(locationList);
        events.add(new Event("Tech Innovators Conference","Showcasing tech advancements",9.2, locationList.get(0)));
        events.add(new Event("Gourmet Food Festival","Culinary delights from chefs",8.7, locationList.get(1)));
        events.add(new Event("Summer Music Carnival","Live music performances outdoors",3.0, locationList.get(2)));
        events.add(new Event("AI and Robotics Expo","Cutting-edge AI and robots",7.5, locationList.get(3)));
        events.add(new Event("Marathon for a Cause","Run for environmental conservation", 9.8, locationList.get(4)));
        events.add(new Event("Art and Craft Fair","Handmade crafts and art",6.0, locationList.get(1)));
        events.add(new Event("Film Screening Night","Classic movie showing event",7.0, locationList.get(2)));
        events.add(new Event("Entrepreneurship Summit","Insights for aspiring entrepreneurs",8.0, locationList.get(3)));
        events.add(new Event("Cultural Heritage Parade","Celebrating diverse cultural traditions",9.0, locationList.get(4)));
        events.add(new Event("Fitness Bootcamp Weekend","Intense outdoor fitness training",7.4, locationList.get(0)));
        this.eventRepository.saveAll(events);
    }
}
