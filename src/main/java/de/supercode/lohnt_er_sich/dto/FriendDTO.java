package de.supercode.lohnt_er_sich.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class FriendDTO {

    private long id;

    private String firstname;

    private String lastname;

    private LocalDate birthday;

    private String phone;

    private String email;

    private String job;

    private Double income;

    private boolean SelfEmployed;

    private boolean wasCustomer;

    private String category;
}
