//package mk.ukim.finki.wp.lab.repository;
//
//import mk.ukim.finki.wp.lab.model.Event;
//import mk.ukim.finki.wp.lab.model.Location;
//import mk.ukim.finki.wp.lab.service.LocationService;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class InMemoryEventRepository {
//    List<Event> events = new ArrayList<>();
//    private final LocationService locationService;
//    public InMemoryEventRepository(LocationService locationService)
//    {
//        this.locationService = locationService;
//        List<Location> locations = locationService.findAll();
//        events.add(new Event("Tech Innovators Conference","Showcasing tech advancements",9.2, locations.get(0)));
//        events.add(new Event("Gourmet Food Festival","Culinary delights from chefs",8.7, locations.get(1)));
//        events.add(new Event("Summer Music Carnival","Live music performances outdoors",3.0, locations.get(2)));
//        events.add(new Event("AI and Robotics Expo","Cutting-edge AI and robots",7.5, locations.get(3)));
//        events.add(new Event("Marathon for a Cause","Run for environmental conservation", 9.8, locations.get(4)));
//        events.add(new Event("Art and Craft Fair","Handmade crafts and art",6.0, locations.get(1)));
//        events.add(new Event("Film Screening Night","Classic movie showing event",7.0, locations.get(2)));
//        events.add(new Event("Entrepreneurship Summit","Insights for aspiring entrepreneurs",8.0, locations.get(3)));
//        events.add(new Event("Cultural Heritage Parade","Celebrating diverse cultural traditions",9.0, locations.get(4)));
//        events.add(new Event("Fitness Bootcamp Weekend","Intense outdoor fitness training",7.4, locations.get(0)));
////    }
//
//    public List<Event> findAll(){
//        return events;
//    }
//    public List<Event> searchEvents(String text, Double rating){
//        return events.stream().filter(x-> (text==null || x.getName().contains(text) || x.getDescription().contains(text)) && (rating==null || x.getPopularityScore()>=rating)).collect(Collectors.toList());
//    }
//
//    public Optional<Event> save(String name, String description, Double popularity, Long id){
//        Location location = locationService.findById(id).get();
//        Event event = new Event(name, description, popularity, location);
//        events.add(event);
//        return  Optional.of(event);
//    }
//
//    public Optional<Event> findById(Long id){
//        return events.stream().filter(x->x.getId().equals(id)).findFirst();
//    }
//    public Optional<Event>update(Long id, String name, String description,  Double popularityScore,  Long locationId)
//    {
//        Event event=findById(id).get();
//        event.setName(name);
//        event.setDescription(description);
//        event.setPopularityScore(popularityScore);
//        event.setLocation(locationService.findById(locationId).get());
//        return Optional.of(event);
//    }
//    public Optional<Event>delete(Long id)
//    {
//        Event event=findById(id).get();
//        events.removeIf(i->i.getId().equals(id));
//        return Optional.of(event);
//    }
//
//    public Optional<Event> increasePoints(Long id){
//        Event event = findById(id). get();
//        double newPop = event.getPopularityScore()+0.5;
//        event.setPopularityScore(newPop);
//        event.setLiked(true);
//        // add save when DB impl
//        return Optional.of(event);
//    }
//
//
//
//}
