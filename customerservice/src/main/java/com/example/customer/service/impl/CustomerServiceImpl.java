package com.example.customer.service.impl;

import com.example.customer.dto.CustomerDTO;
import com.example.customer.entity.Customer;
import com.example.customer.repo.CustomerRepository;
import com.example.customer.service.CustomerService;
import com.example.customer.util.exceptions.CustomerAlreadyExistsException;
import com.example.customer.util.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerDTO getCustomer(Integer id) {
        log.info("Method getCustomer called with id {}", id);
        try {
            Customer customer = customerRepository.findById(id).orElse(new Customer());
            return modelMapper.map(customer, CustomerDTO.class);
        } catch (Exception e) {
            log.error("ERROR: getCustomer failed with id {}", id);
            throw e;
        }
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        log.info("createCustomer called with id {}", customerDTO.getId());
        if (customerRepository.existsByNic(customerDTO.getNic())) {
            throw new CustomerAlreadyExistsException("Customer with NIC " + customerDTO.getNic() + " already exists");
        }
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer = customerRepository.save(customer);
        CustomerDTO savedCustomerDTO = modelMapper.map(customer, CustomerDTO.class);
        log.info("createCustomer success with id {}", savedCustomerDTO.getId());
        return savedCustomerDTO;
    }


    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO,Integer id) {
        log.info("Method updateCustomer called with id {}", customerDTO.getId());
        try{

            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

            customer.setNic(customerDTO.getNic());
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setEmail(customerDTO.getEmail());
            customer.setPhone(customerDTO.getPhone());
            customerRepository.save(customer);

            log.info("updateCustomer success with id {}", customerDTO.getId());
            return modelMapper.map(customer, CustomerDTO.class);
        }catch (Exception e) {
            log.error("ERROR: {}",e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean deleteCustomer(Integer id) {
        log.info("Method deleteCustomer called with id {}", id);
        try {
            if (!customerRepository.existsById(id))
                throw new CustomerNotFoundException("Customer not found");

            customerRepository.deleteById(id);
            log.info("deleteCustomer success with id {}", id);
            return true;
        } catch (Exception e) {
            log.error("ERROR: deleteCustomer failed with id {}", id);
            throw e;
        }
    }

    @Override
    public Page<CustomerDTO> getCustomersWithPagination(int page, int size, String firstName, String lastName, String email, String phone, String nic) {
        try{
            log.info("Method getCustomersWithPagination called with page {}, size {}, firstName {}, lastName {}, email {}, phone {}, nic {}", page, size, firstName, lastName, email, phone, nic);
            Pageable pageable = PageRequest.of(page, size);
            return customerRepository.findCustomerWithPagination(firstName, lastName, email, phone, nic, pageable);
        }catch (RuntimeException e) {
            log.error("ERROR: {}",e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CustomerDTO> getCustomersWithoutPagination(String firstName, String lastName, String email, String phone, String nic) {
        log.info("Method getCustomersWithoutPagination called with firstName {}, lastName {}, email {}, phone {}, nic {}", firstName, lastName, email, phone, nic);
        try{
            return customerRepository.findCustomersWithoutPagination(firstName, lastName, email, phone, nic).stream().map(
                    (element) ->
                            modelMapper.map(element, CustomerDTO.class))
                    .collect(Collectors.toList());
        }catch (Exception e) {
            log.error("ERROR: {}",e.getMessage());
            throw e;
        }
    }
}
