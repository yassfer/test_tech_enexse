package com.enexse.testtechnique.repositories;

import com.enexse.testtechnique.entities.Agent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends CrudRepository<Agent, String> {
    Optional<Agent> findByName(String name);
}
