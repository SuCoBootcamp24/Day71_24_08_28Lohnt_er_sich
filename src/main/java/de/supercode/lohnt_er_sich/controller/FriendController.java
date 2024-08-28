package de.supercode.lohnt_er_sich.controller;

import de.supercode.lohnt_er_sich.dto.FriendDTO;
import de.supercode.lohnt_er_sich.entity.Friend;
import de.supercode.lohnt_er_sich.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friends")
public class FriendController {

    FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Friend>> getAllFriends() {
        List<Friend> friends = friendService.getAllFriends();

        if (friends.size() == 0) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Friend> getFriend(@PathVariable long id) {
        Friend existFriend = friendService.getFriend(id);

        if (existFriend == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(existFriend, HttpStatus.FOUND);
    }



    @PostMapping
    public ResponseEntity<Friend> createFriend(@RequestBody FriendDTO friendDTO) {
        Friend newFriend = friendService.createFriend(friendDTO);

        if (newFriend == null) return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        else return new ResponseEntity<>(newFriend, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Friend> updateFriend(@RequestBody FriendDTO friendDTO) {
        Friend updatedFriend = friendService.updateFriend(friendDTO);

        if (updatedFriend == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(updatedFriend, HttpStatus.OK);
    }

    @DeleteMapping("{if}")
    public ResponseEntity deleteFriend(@PathVariable Long id) {
        if (friendService.deleteFriend(id)) return new ResponseEntity<>(null, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
