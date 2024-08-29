package de.supercode.lohnt_er_sich.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany
    //@JsonIgnoreProperties("category")
    private List<Friend> friendList;



    public void addFriend(Friend friend) {
        friendList.add(friend);
    }

    public void removeFriend(Friend friend) {
        friendList.remove(friend);
    }
}
