package com.etraveli.service;

import com.etraveli.dto.CustomerDTO;
import com.etraveli.dto.MovieDTO;
import com.etraveli.dto.MovieRentalDTO;
import com.etraveli.dto.request.SaveCustomer;
import com.etraveli.entity.Customer;
import com.etraveli.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerService {
  private CustomerRepository customerRepository;

  public CustomerDTO save(SaveCustomer request) {
    Customer entity = customerRepository.save(new Customer(request.customerName()));
    return CustomerDTO.builder()
            .id(entity.getId())
            .name(entity.getName())
            .build();
  }

  public List<CustomerDTO> findAll() {
    List<CustomerDTO> result = new ArrayList<>();
    List<Customer> entities = customerRepository.findAll();
    entities.forEach(entity -> {
            List<MovieRentalDTO> rentals = new ArrayList<>();
            entity.getRentals().forEach(
                    rental -> rentals.add(MovieRentalDTO.builder()
                    .movie(MovieDTO.builder().title(rental.getMovie().getTitle()).build())
                    .days(rental.getDays())
                            .build()));

            result.add(CustomerDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .rentals(rentals)
                    .build());
    });
    return result;
  }

}
