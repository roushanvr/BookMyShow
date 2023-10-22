package com.example.bms.models;

import com.example.bms.models.enums.Features;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel{
    private String number;
    private int maximumRows;
    private int maximumCols;

    @ManyToOne
    private Theatre theatres;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Features> supportedFeatures;

    @OneToMany(mappedBy="auditorium")
    private List<Seat> seats;
    @OneToMany(mappedBy="auditorium")
    private List<MovieShow> shows;
}
