package com.planea.planea_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planea.planea_backend.entities.Event;
import com.planea.planea_backend.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class that handles operations related to {@link Event}.
 * This service provides methods to retrieve, save, and delete events.
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Retrieves all events from the database.
     *
     * @return a list of all events.
     */
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    /**
     * Retrieves an event by its ID.
     *
     * @param id the ID of the event to retrieve.
     * @return an {@link Optional} containing the event if found, or an empty
     *         Optional if not found.
     */
    public Optional<Event> findById(Integer id) {
        return eventRepository.findById(id);
    }

    /**
     * Creates or updates an event in the database.
     *
     * @param event the event to be saved or updated.
     * @return the saved or updated event.
     */
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    /**
     * Deletes an event by its ID.
     *
     * @param id the ID of the event to delete.
     */
    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }
}
