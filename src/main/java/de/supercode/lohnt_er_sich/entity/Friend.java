package de.supercode.lohnt_er_sich.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String phone;

    private String email;

    private String job;

    @Column(nullable = false)
    private Double income;

    @Column(nullable = false)
    private boolean selfEmployed;

    @Column(nullable = false)
    private boolean wasCustomer;

    @ManyToOne
    @JsonIgnoreProperties("friendList") // Verhindert die Serialisierung der Freundesliste innerhalb der Kategorie
    private Category category;
}
