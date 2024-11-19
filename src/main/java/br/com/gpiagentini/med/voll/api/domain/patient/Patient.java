package br.com.gpiagentini.med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Patient")
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
