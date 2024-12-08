package org.sid.bank_account_service.repositories;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.sid.bank_account_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
