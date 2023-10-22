package com.example.bms.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookTicketRequestDTO {
    private List<Long> seatInAShowIds;//we will get a list of seat in a show ids
    private Long userIds;//which user is trying it

}
