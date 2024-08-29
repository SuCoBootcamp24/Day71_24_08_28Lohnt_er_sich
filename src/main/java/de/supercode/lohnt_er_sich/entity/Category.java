package de.supercode.lohnt_er_sich.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "category")
    //@JsonIgnoreProperties("category") // Verhindert die Serialisierung der Kategorie innerhalb jedes Freundes
    private List<Friend> friendList;

    public void addFriend(Friend friend) {
        friendList.add(friend);
    }

    public void removeFriend(Friend friend) {
        friendList.remove(friend);
    }
}
