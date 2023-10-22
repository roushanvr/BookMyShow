package com.example.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class MovieShow extends BaseModel{//Show is a keyword in database,so I used MovieShow
    private Date startTime;
    private Date endTime;
    private int lockingTimeout;
    private int maxTicketBookingAllowed;

    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Auditorium auditorium;
    @OneToMany(mappedBy="show")
    private List<SeatInShow> seatInShows;
    @OneToMany(mappedBy="show")
    private List<SeatTypeInShow> seatTypeInShows;




}
