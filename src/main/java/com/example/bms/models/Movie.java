package com.example.bms.models;

import com.example.bms.models.enums.Features;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String name;
    //in minutes
    private int duration;
    @ManyToMany(mappedBy="movies")
    private List<Actor> actors;
    @OneToMany(mappedBy="movie")
    private List<MovieShow> shows;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Features> requiredFeatures;

}
