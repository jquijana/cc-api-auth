package org.cc.api.auth.domain.repositories;

import org.cc.api.auth.domain.entities.Phone;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends ReactiveCrudRepository<Phone, String> {
}
