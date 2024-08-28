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


    @GetMapping("/search")
    public ResponseEntity<List<Friend>> getFriendsSearchBy(@RequestParam(required = false) Long id,
                                                           @RequestParam(required = false) Double income,
                                                           @RequestParam(required = false) Boolean wasCustomer,
                                                           @RequestParam(required = false) Boolean SelfEmployed,
                                                           @RequestParam(required = false) Integer age) {
        List<Friend> friendsList;

        if (id != null) friendsList = List.of(friendService.getFriend(id));
        else if(income != null && wasCustomer == true) friendsList = friendService.getAllFriendsByIncomeAndWasCustomer(income, wasCustomer);
        else if (income != null && wasCustomer == null) friendsList = friendService.getFriendsByIncome(income);
        else if (SelfEmployed != null) friendsList = friendService.getFriendsByIsSelfEmployed(SelfEmployed);
        else if (age != null) friendsList = friendService.getFriendsByAge(age);
        else friendsList = friendService.getAllFriends();



        if (friendsList.size() == 0) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(friendsList, HttpStatus.OK);
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

    @DeleteMapping("{id}")
    public HttpStatus deleteFriend(@PathVariable Long id) {
        if (friendService.deleteFriend(id)) return HttpStatus.OK;
        else return HttpStatus.NOT_FOUND;
    }

}
