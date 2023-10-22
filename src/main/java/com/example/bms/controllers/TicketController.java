package com.example.bms.controllers;

import com.example.bms.dtos.BookTicketRequestDTO;
import com.example.bms.dtos.BookTicketResponseDTO;
import com.example.bms.dtos.ResponseStatus;
import com.example.bms.models.Ticket;
import com.example.bms.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }
    BookTicketResponseDTO bookTicket(BookTicketRequestDTO bookTicketRequestDTO){
        BookTicketResponseDTO bookTicketResponseDTO=new BookTicketResponseDTO();

        try{
            //call the service
           Ticket ticket=ticketService.bookTicket(bookTicketRequestDTO.getSeatInAShowIds(),bookTicketRequestDTO.getUserIds());

           bookTicketResponseDTO.setIdOfDummyTicket(ticket.getId());
           bookTicketResponseDTO.setStatus(ResponseStatus.SUCCESS);
           bookTicketResponseDTO.setMessage("Dummy Ticket generated successfully, go to payment gateway");

        }
        catch(Exception ex){
            bookTicketResponseDTO.setStatus(ResponseStatus.FAILURE);
            bookTicketResponseDTO.setMessage("Something Wrong Happened");
        }
        return bookTicketResponseDTO;
    }

}
