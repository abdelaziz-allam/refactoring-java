package com.etraveli.repo;

import com.etraveli.entity.MovieRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRentalRepository extends JpaRepository<MovieRental, UUID> {
  List<MovieRental> findByCustomerId(UUID customerId);

}
