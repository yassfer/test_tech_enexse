package com.enexse.testtechnique.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "agents")
public class Agent {

    @Column(nullable = false, unique = true)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @Column(nullable = false, unique = true)
    private String name;
    private String os;
    private String ip;
    private String version;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Date lastKeepAlive;
    private Date dateAdd;
}
