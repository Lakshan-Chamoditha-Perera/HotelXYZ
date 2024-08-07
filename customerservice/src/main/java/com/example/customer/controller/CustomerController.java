package com.example.customer.controller;

import com.example.customer.dto.CustomerDTO;
import com.example.customer.payloads.StandardResponse;
import com.example.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<StandardResponse> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .code(200)
                        .message("Customer created successfully")
                        .data(customerService.createCustomer(customerDTO))
                        .build()
        );
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable("customerId") Integer customerId) {
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .code(200)
                        .message("Customer deleted successfully")
                        .data(customerService.deleteCustomer(customerId))
                        .build()
        );
    }

    @GetMapping(value = "/{customerId}")
    public CustomerDTO getCustomer(@PathVariable("customerId") int customerId) {
        return customerService.getCustomer(customerId);
    }

    @PatchMapping(value = "/{customerId}")
    public ResponseEntity<StandardResponse> updateCustomer(@PathVariable("customerId") int customerId,@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .code(200)
                        .message("Customer updated successfully")
                        .data(customerService.updateCustomer(customerDTO,customerId)).build()
        );
    }

}
