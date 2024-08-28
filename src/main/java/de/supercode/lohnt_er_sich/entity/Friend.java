package de.supercode.lohnt_er_sich.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Friend")
@Data
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private LocalDate birthday;

    private String mobile;

    private String email;

    private String job;

    @Column(nullable = false)
    private double income;

    @Column(nullable = false)
    private boolean isSelfEmployed;

    @Column(nullable = false)
    private boolean wasCustomer;

}
