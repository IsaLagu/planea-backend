package com.planea.planea_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.planea.planea_backend.dtos.EventDto;
import com.planea.planea_backend.entities.Category;
import com.planea.planea_backend.entities.City;
import com.planea.planea_backend.entities.Event;
import com.planea.planea_backend.entities.User;
import com.planea.planea_backend.repositories.UserRepository;
import com.planea.planea_backend.services.CategoryService;
import com.planea.planea_backend.services.CityService;
import com.planea.planea_backend.services.EventService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<Event>> getAllUserEvents() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        String userEmail = currentUser.getUsername();
        Optional<User> currentUserOptional = userRepository.findByEmail(userEmail);

        if (!currentUserOptional.isPresent()) {
            return ResponseEntity.status(403).build();
        }

        List<Event> events = eventService.findAllByUserId(currentUserOptional.get().getId());

        return ResponseEntity.ok(events);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer id) {
        Optional<Event> event = eventService.findById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(
            @RequestBody EventDto eventDto) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Event event = new Event();

            event.setTitle(eventDto.getTitle());
            event.setDescription(eventDto.getDescription());
            event.setLocation(eventDto.getLocation());
            event.setStartDate(eventDto.getStartDate());
            event.setEndDate(eventDto.getEndDate());
            event.setImageUrl(eventDto.getImageUrl());
            event.setPrice(eventDto.getPrice());
            event.setCapacity(eventDto.getCapacity());
            event.setIsActive(true);

            User currentUser = (User) authentication.getPrincipal();
            event.setUser(currentUser);

            City city = cityService.findById(eventDto.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found with id: " + eventDto.getCityId()));
            event.setCity(city);

            List<Category> categories = categoryService.findByIds(eventDto.getCategoryIds());
            event.setCategories(categories);

            Event savedEvent = eventService.save(event);
            return ResponseEntity.ok(savedEvent);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Aquí puedes personalizar el mensaje de error
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Integer id,
            @RequestBody EventDto eventDto) {
        Optional<Event> eventOptional = eventService.findById(id);

        if (eventOptional.isPresent()) {
            try {
                Event event = eventOptional.get();

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                event.setTitle(eventDto.getTitle());
                event.setDescription(eventDto.getDescription());
                event.setLocation(eventDto.getLocation());
                event.setStartDate(eventDto.getStartDate());
                event.setEndDate(eventDto.getEndDate());
                event.setImageUrl(eventDto.getImageUrl());
                event.setPrice(eventDto.getPrice());
                event.setCapacity(eventDto.getCapacity());

                User currentUser = (User) authentication.getPrincipal();
                event.setUser(currentUser);

                City city = cityService.findById(eventDto.getCityId())
                        .orElseThrow(() -> new RuntimeException("City not found with id: " + eventDto.getCityId()));
                event.setCity(city);

                List<Category> categories = categoryService.findByIds(eventDto.getCategoryIds());
                event.setCategories(categories);
                Event updatedEvent = eventService.save(event);
                return ResponseEntity.ok(updatedEvent);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(null); // Manejo de error si la ciudad/categorías no son válidas
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Event>> deleteEvent(@PathVariable Integer id) {
        Optional<Event> eventOptional = eventService.findById(id);

        if (eventOptional.isPresent()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            String userEmail = currentUser.getUsername();
            Optional<User> currentUserOptional = userRepository.findByEmail(userEmail);

            if (currentUserOptional.isPresent()
                    && currentUserOptional.get().getId().equals(eventOptional.get().getUser().getId())) {
                eventService.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
