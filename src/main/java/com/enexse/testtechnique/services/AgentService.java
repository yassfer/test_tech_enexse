package com.enexse.testtechnique.services;

import com.enexse.testtechnique.entities.Agent;
import com.enexse.testtechnique.entities.AgentView;

import java.util.Optional;

public interface AgentService {
    Agent saveAgent(Agent agent);

    Optional<Agent> findAgentById(String id);

    Iterable<Agent> findAllAgents();

    boolean deleteAgent(String id);

    Agent updateAgent(String name, Agent agent);

    AgentView getAgentsByStatus();
}
