package com.example.customer.repo;

import com.example.customer.dto.CustomerDTO;
import com.example.customer.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Boolean existsByNic(String nic);


    @Query("SELECT new com.example.customer.dto.CustomerDTO(c.id, c.firstName, c.lastName, c.email, c.phone, c.nic) " +
            "FROM Customer c " +
            "WHERE (?1 IS NULL OR c.firstName LIKE %?1%) " +
            "AND (?2 IS NULL OR c.lastName LIKE %?2%) " +
            "AND (?3 IS NULL OR c.email LIKE %?3%) " +
            "AND (?4 IS NULL OR c.phone LIKE %?4%) " +
            "AND (?5 IS NULL OR c.nic LIKE %?5%) ORDER BY c.id DESC ")
    Page<CustomerDTO> findCustomerWithPagination(String firstName, String lastName, String email, String phone, String nic, Pageable pageable);

    @Query(value = "SELECT * FROM customer " +
                    "WHERE (?1 IS NULL OR first_name LIKE %?1%) " +
                    "AND (?2 IS NULL OR last_name LIKE %?2%) " +
                    "AND (?3 IS NULL OR email LIKE %?3%) " +
                    "AND (?4 IS NULL OR phone LIKE %?4%) " +
                    "AND (?5 IS NULL OR nic LIKE %?5%) ORDER BY id DESC ", nativeQuery = true )
    List<Customer> findCustomersWithoutPagination(String firstName, String lastName, String email, String phone, String nic);
}
