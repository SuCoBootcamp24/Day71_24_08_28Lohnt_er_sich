package de.supercode.lohnt_er_sich.repository;


import de.supercode.lohnt_er_sich.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
