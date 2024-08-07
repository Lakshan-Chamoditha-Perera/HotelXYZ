package com.example.customer.service.impl;

import com.example.customer.dto.CustomerDTO;
import com.example.customer.entity.Customer;
import com.example.customer.repo.CustomerRepository;
import com.example.customer.service.CustomerService;
import com.example.customer.util.exceptions.CustomerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
            if (!customerRepository.existsById(id))
                return new CustomerDTO();

            return modelMapper.map(customerRepository.findById(id).get(), CustomerDTO.class);
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

            if (!customerRepository.existsById(id))
                throw new RuntimeException("Customer not found");

            Customer customer = customerRepository.findById(id).get();

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
                throw new RuntimeException("Customer not found");

            customerRepository.deleteById(id);
            log.info("deleteCustomer success with id {}", id);
            return true;
        } catch (Exception e) {
            log.error("ERROR: deleteCustomer failed with id {}", id);
            throw e;
        }
    }
}
