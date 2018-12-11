package com.matrangola.indicator.data.repository;

import com.matrangola.indicator.data.model.Customer;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends CassandraRepository<Customer, UUID> {
    List<Customer> findCustomersByZipcode(int zipcode);
    Optional<Customer> findCustomerByEmail(String email);
}