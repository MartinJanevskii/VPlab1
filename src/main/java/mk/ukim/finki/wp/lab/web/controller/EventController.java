package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/events")
@Controller
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model){
        List<Event> eventList = eventService.listAll();
        model.addAttribute("events",eventList);
        return "listEvents";
    }

    @PostMapping("/save")
    public String saveEvent(@RequestParam String name,
                           @RequestParam String description,
                            @RequestParam double popularity,
                            @RequestParam Long locationId){

            eventService.save(name, description, popularity, locationId);

        return "redirect:/events";
    }

    @GetMapping("/add")
    public String showAdd(Model model){
        model.addAttribute("locations",locationService.findAll());
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @GetMapping("/{id}/edit")
    public String showEditEvent(@PathVariable Long id, Model model){
        Event event = eventService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid id"+id));
        model.addAttribute("event",event);
        model.addAttribute("locations",locationService.findAll());

        return "editEvent";
    }

    @PostMapping("/saveEdit")
    public String showEdited(@RequestParam(required = false) Long id,
            @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam double popularity,
                             @RequestParam Long locationId
                             ) {
        eventService.update(id, name, description, popularity, locationId);

        return "redirect:/events";
    }

    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id){
        eventService.delete(id);
        return "redirect:/events";
    }
}
