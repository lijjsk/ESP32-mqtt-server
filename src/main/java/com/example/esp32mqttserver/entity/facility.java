package com.example.esp32mqttserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class facility {
    @Id
    private String clientId;
    @OneToOne(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "gid")
    private good gId;
}
