package com.enexse.testtechnique.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class AgentView {
    Long totalAgent;
    Map<Status, Long> agentStatus;
}
