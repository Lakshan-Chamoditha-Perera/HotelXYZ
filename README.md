# HotelXYZ Microservices Project

## Overview

HotelXYZ is a microservices-based project designed to manage hotel operations efficiently. The system utilizes Spring Boot and a suite of Spring Cloud technologies, providing a scalable, robust, and flexible architecture.

## Features

- **Comprehensive Hotel Management**: Manage bookings, customer data, and room availability.
- **Service Discovery**: Uses Eureka for dynamic discovery and communication between microservices.
- **API Gateway**: Centralized entry point for client requests, handling routing, security, and load balancing.
- **Asynchronous Communication**: Implemented with Kafka and Zookeeper for reliable, scalable message-driven interactions.
- **Efficient Data Management**: Powered by Spring Data, integrated with MySQL for robust data storage and retrieval.

## Technologies Used

- **Java**: Core language for developing microservices.
- **Spring Boot**: Framework for building microservices.
- **Spring Data**: Simplified data access, integrated with MySQL.
- **Kafka**: For distributed messaging and asynchronous communication.
- **Zookeeper**: Used for managing and coordinating Kafka brokers.
- **Eureka**: Service discovery server for locating microservices.
- **API Gateway**: Acts as a single entry point for all microservices.
- **Spring Cloud**: For building cloud-native applications using distributed systems patterns.

## Microservices Architecture

1. **apigateway**: Handles routing, load balancing, and security for all incoming requests.
2. **base**: Common configurations and utilities shared across all services.
3. **bookingservice**: Manages room bookings, cancellations, and related operations.
4. **customerservice**: Handles customer profiles, preferences, and history.
5. **discoveryserver**: Eureka server for service discovery and registration.
6. **roomservice**: Manages room inventory, availability, and details.

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- Apache Kafka with Zookeeper
- MySQL database
- Eureka Server
- API Gateway
