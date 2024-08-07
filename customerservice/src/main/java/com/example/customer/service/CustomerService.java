package com.example.customer.service;

import com.example.customer.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO getCustomer(Integer id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO,Integer id);

    Boolean deleteCustomer(Integer id);

}

