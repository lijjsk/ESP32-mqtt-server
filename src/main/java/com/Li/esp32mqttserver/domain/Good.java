package com.Li.esp32mqttserver.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gid;
    private String name;
    private Double price;
    private String goodsDetails;
    private Long stock;
}
