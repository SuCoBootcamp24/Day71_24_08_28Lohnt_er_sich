package de.supercode.lohnt_er_sich.service;

import de.supercode.lohnt_er_sich.dto.FriendDTO;
import de.supercode.lohnt_er_sich.entity.Friend;
import de.supercode.lohnt_er_sich.mapper.FriendMapper;
import de.supercode.lohnt_er_sich.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendService {

    FriendRepository friendRepository;
    FriendMapper friendMapper;

    public FriendService(FriendRepository friendRepository, FriendMapper friendMapper) {
        this.friendRepository = friendRepository;
        this.friendMapper = friendMapper;
    }



    public List<Friend> getAllFriends() {
        return friendRepository.findAll();
    }

    public Friend getFriend(Long id) {
        return friendRepository.findById(id).orElse(null);
    }


    public Friend createFriend(FriendDTO friendDTO) {
        Optional<Friend> existFriend = friendRepository.findByFirstnameAndLastname(friendDTO.getFirstname(), friendDTO.getLastname());
        if (existFriend.isEmpty()) return friendRepository.save(friendMapper.toEntity(friendDTO));
        else return null;
    }


    public Friend updateFriend(FriendDTO friendDTO) {
        Optional<Friend> existFriend = friendRepository.findById(friendDTO.getId());
        if (existFriend.isPresent()) {
            Friend friend = existFriend.get();
            friend.setFirstname(friendDTO.getFirstname());
            friend.setLastname(friendDTO.getLastname());
            friend.setBirthday(friendDTO.getBirthday());
            friend.setPhone(friendDTO.getPhone());
            friend.setEmail(friendDTO.getEmail());
            friend.setJob(friendDTO.getJob());
            friend.setIncome(friendDTO.getIncome());
            friend.setSelfEmployed(friendDTO.isSelfEmployed());
            return friendRepository.save(friend);
        }
        return null;
    }

    public boolean deleteFriend(Long id) {
        Optional<Friend> existFriend = friendRepository.findById(id);
        if (existFriend.isPresent()) {
            friendRepository.delete(existFriend.get());
            return true;
        }
        return false;
    }

    public List<Friend> getAllFriendsByIncomeAndWasCustomer(Double income, Boolean wasCustomer) {
        return friendRepository.findAllByIncomeGreaterThanAndWasCustomer(income, wasCustomer).stream()
                .sorted(Comparator.comparing(Friend::getIncome).reversed())
                .collect(Collectors.toList());
    }

    public List<Friend> getFriendsByIncome(Double income) {
        return friendRepository.findAllByIncomeGreaterThan(income).stream()
               .sorted(Comparator.comparing(Friend::getIncome).reversed())
               .collect(Collectors.toList());
    }


    public List<Friend> getFriendsByIsSelfEmployed(Boolean isSelfEmployed) {
        return friendRepository.findAllBySelfEmployed(isSelfEmployed).stream()
                .sorted(Comparator.comparing(Friend::getIncome).reversed())
                .collect(Collectors.toList());
    }

    public List<Friend> getFriendsByAge(Integer age) {
        LocalDate today = LocalDate.now();
        return getAllFriends().stream()
                .filter(friend -> {
                    int friendAge = Period.between(friend.getBirthday(), today).getYears();
                    return friendAge > age;
                })
                .sorted((f1, f2) -> f2.getBirthday().compareTo(f1.getBirthday())) // Sortiere absteigend nach Alter
                .collect(Collectors.toList());
    }
}
