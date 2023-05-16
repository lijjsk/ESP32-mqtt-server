package com.Li.esp32mqttserver.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uid;
    private String name;
    private String pass;
}
