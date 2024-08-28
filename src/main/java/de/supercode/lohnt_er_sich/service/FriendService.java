package de.supercode.lohnt_er_sich.service;

import de.supercode.lohnt_er_sich.dto.FriendDTO;
import de.supercode.lohnt_er_sich.entity.Friend;
import de.supercode.lohnt_er_sich.mapper.FriendMapper;
import de.supercode.lohnt_er_sich.repository.FriendRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

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

    public Friend getFriend(long id) {
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
}
