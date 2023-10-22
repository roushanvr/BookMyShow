package com.example.bms.repositories;

import com.example.bms.models.SeatInShow;
import com.example.bms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatInShowRepository extends JpaRepository<SeatInShow, Long> {
    @Override
    List<SeatInShow> findAllById(Iterable<Long> longs);

    @Override
    SeatInShow save(SeatInShow entity);
}

