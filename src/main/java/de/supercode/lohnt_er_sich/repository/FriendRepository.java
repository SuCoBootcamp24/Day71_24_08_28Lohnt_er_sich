package de.supercode.lohnt_er_sich.repository;


import de.supercode.lohnt_er_sich.entity.Category;
import de.supercode.lohnt_er_sich.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {


    Optional<Friend> findByFirstnameAndLastname(String firstname, String lastname);

    List<Friend> findAllByIncomeGreaterThanEqualAndWasCustomer(Double income, Boolean wasCustomer);

    List<Friend> findAllBySelfEmployed(Boolean isSelfEmployed);

    List<Friend> findAllByIncomeGreaterThanEqual(Double income);

    List<Friend> findAllByWasCustomer(Boolean wasCustomer);

    List<Friend> findAllByCategory(Category category);
}
