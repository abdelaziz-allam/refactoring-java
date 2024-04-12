package com.etraveli.service;

import com.etraveli.dto.CustomerDTO;
import com.etraveli.dto.CustomerDTOBuilder;
import com.etraveli.dto.request.SaveCustomer;
import com.etraveli.dto.request.SaveCustomerBuilder;
import com.etraveli.entity.Customer;
import com.etraveli.repo.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerServiceTest {


  @Mock
  private CustomerRepository customerRepository;
  @InjectMocks
  private CustomerService customerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSave() {
    UUID customerId = UUID.randomUUID();
    String customerName = "Abdelaziz Allam";
    SaveCustomer request = SaveCustomerBuilder.builder()
            .customerName("Test Category")
            .build();
    CustomerDTO expected = CustomerDTOBuilder.builder().id(customerId).name(customerName).rentals(null).build();;
    Customer entity = new Customer(customerId, customerName,new ArrayList<>());

    when(customerRepository.save(any())).thenReturn(entity);

    CustomerDTO response = customerService.save(request);

    assertNotNull(response);
    assertThat(expected, is(response));
  }

  @Test
  void testFindAll() {
    List<Customer> customerList = List.of(
            new Customer(UUID.randomUUID(), "Tommy C.C", null),
            new Customer(UUID.randomUUID(), "Abdelaziz Aam", null)
    );
    List<CustomerDTO> expected = List.of(
            CustomerDTOBuilder.builder().id(UUID.randomUUID()).name("Tommy C.C").rentals(null).build(),
            CustomerDTOBuilder.builder().id(UUID.randomUUID()).name("Abdelaziz Aam").rentals(null).build()
    );

    when(customerRepository.findAll()).thenReturn(customerList);

    List<CustomerDTO> response = customerService.findAll();

    assertNotNull(response);
    assertThat(Objects.requireNonNull(response), hasSize(expected.size()));

  }
}