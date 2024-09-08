package com.planea.planea_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planea.planea_backend.entities.Event;
import com.planea.planea_backend.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Get all the events
     * 
     * @return List of events
     */
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    /**
     * Search an event by ID
     * 
     * @param id
     * @return Event if exists, or an Optional empty
     */
    public Optional<Event> findById(Integer id) {
        return eventRepository.findById(id);
    }

    /**
     * Create or update an event
     * 
     * @param event       Event to save
     * @param cityID      ID of the city related to the event
     * @param categoryIds List of IDs from the associated categories
     * @return The saved event
     */
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    /**
     * Delete an event by ID
     * 
     * @param id ID of the event to delete
     */
    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }
}
