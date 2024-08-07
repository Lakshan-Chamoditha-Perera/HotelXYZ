package com.example.customer.service;

import com.example.customer.dto.CustomerDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    CustomerDTO getCustomer(Integer id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO,Integer id);

    Boolean deleteCustomer(Integer id);

    Page<CustomerDTO> getCustomersWithPagination(int page, int size, String firstName, String lastName, String email, String phone, String nic);

    List<CustomerDTO> getCustomersWithoutPagination(String firstName, String lastName, String email, String phone, String nic);
}

