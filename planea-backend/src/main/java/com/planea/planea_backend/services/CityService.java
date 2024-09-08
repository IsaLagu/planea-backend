package com.planea.planea_backend.services;

import com.planea.planea_backend.entities.City;
import com.planea.planea_backend.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that handles operations related to {@link City}.
 * This service provides methods to retrieve, save, and delete cities.
 */
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    /**
     * Retrieves all cities from the database.
     *
     * @return a list of all cities.
     */
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    /**
     * Retrieves a city by its ID.
     *
     * @param id the ID of the city to retrieve.
     * @return an {@link Optional} containing the city if found, or an empty
     *         Optional if not found.
     */
    public Optional<City> findById(String id) {
        return cityRepository.findById(id);
    }

    /**
     * Saves a new city or updates an existing city in the database.
     *
     * @param city the city to be saved or updated.
     * @return the saved or updated city.
     */
    public City save(City city) {
        return cityRepository.save(city);
    }

    /**
     * Deletes a city by its ID.
     *
     * @param id the ID of the city to delete.
     */
    public void deleteById(String id) {
        cityRepository.deleteById(id);
    }
}
