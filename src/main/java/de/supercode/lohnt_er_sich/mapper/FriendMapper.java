package de.supercode.lohnt_er_sich.mapper;

import de.supercode.lohnt_er_sich.dto.FriendDTO;
import de.supercode.lohnt_er_sich.entity.Friend;
import org.springframework.stereotype.Component;

@Component
public class FriendMapper {
    public Friend toEntity(FriendDTO friendDTO) {
        Friend friend = new Friend();
        friend.setId(friendDTO.getId());
        friend.setFirstname(friendDTO.getFirstname());
        friend.setLastname(friendDTO.getLastname());
        friend.setBirthday(friendDTO.getBirthday());
        friend.setPhone(friendDTO.getPhone());
        friend.setEmail(friendDTO.getEmail());
        friend.setJob(friendDTO.getJob());
        friend.setIncome(friendDTO.getIncome());
        friend.setSelfEmployed(friendDTO.isSelfEmployed());
        friend.setWasCustomer(friendDTO.isWasCustomer());
        return friend;
    }

    public FriendDTO toDTO(Friend friend) {
        FriendDTO friendDTO = new FriendDTO();
        friendDTO.setId(friend.getId());
        friendDTO.setFirstname(friend.getFirstname());
        friendDTO.setLastname(friend.getLastname());
        friendDTO.setBirthday(friend.getBirthday());
        friendDTO.setPhone(friend.getPhone());
        friendDTO.setEmail(friend.getEmail());
        friendDTO.setJob(friend.getJob());
        friendDTO.setIncome(friend.getIncome());
        friendDTO.setSelfEmployed(friend.isSelfEmployed());
        friendDTO.setWasCustomer(friend.isWasCustomer());
        return friendDTO;
    }
}
