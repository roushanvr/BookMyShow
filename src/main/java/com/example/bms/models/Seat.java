package com.example.bms.models;

import com.example.bms.models.enums.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String number;
    private int rowNum;
    private int colNum;

    @ManyToOne
    private Auditorium auditorium;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    @OneToMany(mappedBy="seat")
    private List<SeatInShow> seatInShows;
}
