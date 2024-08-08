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

    @GetMapping()
    public ResponseEntity<StandardResponse> getCustomers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "0", required = false) int size,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "nic", required = false) String nic) {

        if (size == 0) {
            return ResponseEntity.ok(StandardResponse.builder()
                    .code(200)
                    .message("Customers retrieved successfully")
                    .data(customerService.getCustomersWithoutPagination(firstName, lastName, email, phone, nic))
                    .build());
        }

        return ResponseEntity.ok(StandardResponse.builder()
                .code(200)
                .message("Customers retrieved successfully")
                .data(customerService.getCustomersWithPagination(page, size, firstName, lastName, email, phone, nic))
                .build());
    }


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
