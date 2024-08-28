package de.supercode.lohnt_er_sich.controller;

import de.supercode.lohnt_er_sich.entity.Friend;
import de.supercode.lohnt_er_sich.service.FriendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friends")
public class FriendController {

    FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/all")
    public List<Friend> getAllFriends() {
        return friendService.getAllFriends();
    }


}
