package com.telus.assesment.model;

import com.telus.assesment.enums.Status;
import jakarta.persistence.*;
import lombok.*;


@EntityListeners({Entity.class})
@Entity
@Table(name = "TODO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
}
