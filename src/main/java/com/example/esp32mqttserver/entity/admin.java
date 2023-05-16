package com.example.esp32mqttserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class admin {
    @Id
    @GenericGenerator(name="",strategy = "")
    @GeneratedValue(generator = "")
    private String uid;
    private String name;
    private String pass;
}
