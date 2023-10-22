package com.example.bms.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDTO {
    private Long idOfDummyTicket;
    private ResponseStatus status;
    private String message;

}
