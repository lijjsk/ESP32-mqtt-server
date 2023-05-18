package com.Li.esp32mqttserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;
    @OneToOne(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "gid")
    private Good gId;
}
