package com.enexse.testtechnique.services;

import com.enexse.testtechnique.entities.Agent;
import com.enexse.testtechnique.entities.AgentView;
import com.enexse.testtechnique.entities.Status;
import com.enexse.testtechnique.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentServiceImp implements AgentService {
    private final AgentRepository agentRepository;

    @Autowired
    AgentServiceImp(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    /**
     * This method allows saving agent
     **/
    @Override
    public Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    /**
     * This method allows returning agent by ID
     **/
    @Override
    public Optional<Agent> findAgentById(String id) {
        return Optional.ofNullable(agentRepository.findById(id).orElse(null));
    }

    /**
     * This method allows deleting agent by ID
     **/
    @Override
    public Iterable<Agent> findAllAgents() {
        return Optional.of(agentRepository.findAll()).orElse(null);
    }

    /**
     * This method allows returning all agents
     **/
    @Override
    public boolean deleteAgent(String id) {
        Optional<Agent> agent = agentRepository.findById(id);
        if (agent.isPresent()) {
            agentRepository.deleteById(agent.get().getId());
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method allows updating agents by name
     **/
    @Override
    public Optional<Agent> updateAgent(String name, Agent agent) {
        Optional<Agent> savedAgent = agentRepository.findByName(name);
        if (savedAgent.isPresent()) {
            savedAgent.get().setName(agent.getName());
            savedAgent.get().setIp(agent.getIp());
            savedAgent.get().setDateAdd(agent.getDateAdd());
            savedAgent.get().setOs(agent.getOs());
            savedAgent.get().setStatus(agent.getStatus());
            savedAgent.get().setVersion(agent.getVersion());
            savedAgent.get().setLastKeepAlive(agent.getLastKeepAlive());
            return savedAgent;
        } else {
            return Optional.empty();
        }
    }

    /**
     * This method allows to return the installed agents by their status
     **/
    @Override
    public AgentView getAgentsByStatus() {
        AgentView agentView = AgentView.builder().build();
        Iterable<Agent> agents = agentRepository.findAll();
        List<Agent> agentList = new ArrayList<>();
        agents.forEach(agentList::add);
        Map<Status, Long> grouped = agentList.stream().collect(Collectors.groupingBy(Agent::getStatus, Collectors.counting()));

        agentView.setAgentStatus(grouped);
        Long total = agentRepository.count();

        agentView.setTotalAgent(total);
        return agentView;
    }
}
