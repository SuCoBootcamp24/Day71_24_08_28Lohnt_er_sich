package de.supercode.lohnt_er_sich.service;

import de.supercode.lohnt_er_sich.entity.Friend;
import de.supercode.lohnt_er_sich.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {

    FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<Friend> getAllFriends() {
        return friendRepository.findAll();
    }
}
