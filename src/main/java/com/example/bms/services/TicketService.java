package com.example.bms.services;
import com.example.bms.exceptions.SeatNotAvailableException;
import com.example.bms.exceptions.UserNotFoundException;
import com.example.bms.models.SeatInShow;
import com.example.bms.models.Ticket;
import com.example.bms.models.User;
import com.example.bms.models.enums.SeatStatus;
import com.example.bms.repositories.SeatInShowRepository;
import com.example.bms.repositories.TicketRepository;
import com.example.bms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private UserRepository userRepository;
    private SeatInShowRepository seatInShowRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository,UserRepository userRepository,SeatInShowRepository seatInShowRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.seatInShowRepository = seatInShowRepository;
    }
    public Ticket bookTicket(List<Long> seatInAShowIds, Long userId) throws SeatNotAvailableException,UserNotFoundException{
        //fetch SeatInShows
        //check if seats are available
        //fetch user
        //if yes.Lock seats, make dummy ticket and return
        //no implies throw an exception

        //We require three repositories userrepository, seatinshowrepository,ticketrepository

       //first seats
       List<SeatInShow> seats= seatInShowRepository.findAllById(seatInAShowIds);
       for(SeatInShow seat:seats){
           if(isAvailable(seat)==false){
               throw new SeatNotAvailableException();
           }
       }

       Optional<User> userOptional= userRepository.findById(userId);
       if(userOptional.isEmpty()){
           throw new UserNotFoundException();
       }
       User bookedBy= userOptional.get();

       //Locking the seats now, saving it in the database, and add updated seats to new list
        List<SeatInShow> updatedSeats=new ArrayList<>();
        Date current =new Date();
        for(SeatInShow seat: seats){
            seat.setSeatStatus(SeatStatus.LOCKED);
            seat.setLockedAt(current);
            //tell the database also. for this you have to save it
            seat=seatInShowRepository.save(seat);
            updatedSeats.add(seat);
        }

        //make dummy ticket and return
        Ticket ticket =new Ticket();
        ticket.setBookedBy(bookedBy);
        ticket.setBookingTime(current);
        ticket.setSelectedSeats(updatedSeats);
        ticket.setAmount(0);//hit SeatTypeInShow Repository for calculating amount

        Ticket savedTicket=ticketRepository.save(ticket);
        return savedTicket;
    }

    private boolean isAvailable(SeatInShow seat){
        if(seat.getSeatStatus().equals(SeatStatus.AVAILABLE)){
            return true;
        }
        else if(seat.getSeatStatus().equals(SeatStatus.LOCKED)){
            long lockedAt=seat.getLockedAt().getTime();
            long current=System.currentTimeMillis();
            long duration=current-lockedAt;
            long durationInseconds=duration/1000;
            long durationInMinutes=durationInseconds/60;

            if(durationInMinutes>=10){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
}
