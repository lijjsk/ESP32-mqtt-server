package com.Li.esp32mqttserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class facility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String clientId;
    @OneToOne(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "gid")
    private good gId;
}
