package com.planea.planea_backend.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @Column(name = "id", length = 10)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    private Set<Event> events;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
