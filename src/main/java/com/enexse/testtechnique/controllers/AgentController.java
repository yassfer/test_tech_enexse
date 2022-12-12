package com.enexse.testtechnique.controllers;

import com.enexse.testtechnique.entities.Agent;
import com.enexse.testtechnique.entities.AgentView;
import com.enexse.testtechnique.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/v1/")
public class AgentController {
    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    /**
     * Create - Save one agent
     *
     * @return - An object of saved agent
     */
    @PostMapping("agent")
    public ResponseEntity<Agent> saveAgent(@RequestBody Agent agent) {
        Agent savedAgent = agentService.saveAgent(agent);
        return new ResponseEntity<>(savedAgent, HttpStatus.CREATED);
    }

    /**
     * Read - Get agent by id
     *
     * @return - An object of the agent
     */
    @GetMapping("agents/{id}")
    public ResponseEntity<?> getAgentById(@PathVariable("id") String id) {
        Optional<Agent> agent = agentService.findAgentById(id);
        if (agent.isPresent()) {
            return new ResponseEntity<>(agent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(MessageFormat.format("There is not agent with id {0} !", id), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Read - Get all agents
     *
     * @return - An Iterable object of agent full filled
     */
    @GetMapping("agents")
    public ResponseEntity<List<Agent>> getAllAgents() {
        List<Agent> agents = (List<Agent>) agentService.findAllAgents();
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }

    /**
     * Delete - Delete agent by id
     *
     * @return - A message
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable("id") String id) {
        if (agentService.deleteAgent(id)) {
            return new ResponseEntity<>("Agent deleted successfully !", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(MessageFormat.format("There is not agent with id {0} !", id), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update - Update agent by name
     *
     * @return - An object of the updated agent
     */
    @PutMapping("{name}")
    public ResponseEntity<Agent> updateAgent(@PathVariable("name") String name, @RequestBody Agent agent) {
        Agent savedAgent = agentService.updateAgent(name, agent);
        if (savedAgent != null) {
            return new ResponseEntity<>(savedAgent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Read - Get all agents by their status
     *
     * @return - An object contining the total number of agents
     * And the number of agents by status
     */
    @GetMapping("agents/byStatus")
    public ResponseEntity<AgentView> getAgentsByStatus() {
        AgentView agentView = agentService.getAgentsByStatus();
        return new ResponseEntity<>(agentView, HttpStatus.OK);
    }
}
