package de.supercode.lohnt_er_sich.service;

import de.supercode.lohnt_er_sich.dto.FriendDTO;
import de.supercode.lohnt_er_sich.entity.Category;
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
    CategoryService categoryService;

    public FriendService(FriendRepository friendRepository, FriendMapper friendMapper,  CategoryService categoryService) {
        this.friendRepository = friendRepository;
        this.friendMapper = friendMapper;
        this.categoryService = categoryService;
    }



    public List<Friend> getAllFriends() {
        return friendRepository.findAll();
    }

    public Friend getFriend(Long id) {
        return friendRepository.findById(id).orElse(null);
    }


    public Friend createFriend(FriendDTO friendDTO) {
        Optional<Friend> existFriend = friendRepository.findByFirstnameAndLastname(friendDTO.getFirstname(), friendDTO.getLastname());
        if (existFriend.isEmpty()) {
            Friend newFriend = friendRepository.save(friendMapper.toEntity(friendDTO));
            if (friendDTO.getCategory() != null) categoryService.addFriendToCategory(newFriend, newFriend.getCategory());
            return newFriend;
        }
        else return null;
    }


    public Friend updateFriend(FriendDTO friendDTO) {
        return friendRepository.findById(friendDTO.getId())
                .map(friend -> {
                    // Aktualisiere nur die Felder, die sich ändern müssen
                    updateNonNullFields(friendDTO, friend);
                    updateCategory(friendDTO, friend);
                    return friendRepository.save(friend);
                })
                .orElse(null);
    }

    private void updateNonNullFields(FriendDTO friendDTO, Friend friend) {
        if (friendDTO.getFirstname() != null) friend.setFirstname(friendDTO.getFirstname());
        if (friendDTO.getLastname() != null) friend.setLastname(friendDTO.getLastname());
        if (friendDTO.getBirthday() != null) friend.setBirthday(friendDTO.getBirthday());
        if (friendDTO.getPhone() != null) friend.setPhone(friendDTO.getPhone());
        if (friendDTO.getEmail() != null) friend.setEmail(friendDTO.getEmail());
        if (friendDTO.getJob() != null) friend.setJob(friendDTO.getJob());
        if (friendDTO.getIncome() != null) friend.setIncome(friendDTO.getIncome());
        friend.setSelfEmployed(friendDTO.isSelfEmployed());
        friend.setWasCustomer(friendDTO.isWasCustomer());
    }

    private void updateCategory(FriendDTO friendDTO, Friend friend) {
        String categoryName = friendDTO.getCategory();

        if (categoryName != null) {
            categoryService.getCategoryByName(categoryName).ifPresent(cat -> {
                if (!cat.equals(friend.getCategory())) {
                    if (friend.getCategory() != null) {
                        categoryService.removeFriendFromCategory(friend, friend.getCategory());
                    }
                    friend.setCategory(cat);
                    categoryService.addFriendToCategory(friend, cat);
                }
            });
        } else if (friend.getCategory() != null) {
            categoryService.removeFriendFromCategory(friend, friend.getCategory());
            friend.setCategory(null);
        }
    }

    public boolean deleteFriend(Long id) {
        Optional<Friend> existFriend = friendRepository.findById(id);
        if (existFriend.isPresent()) {
            categoryService.removeFriendFromCategory(existFriend.get(), existFriend.get().getCategory());
            friendRepository.delete(existFriend.get());
            return true;
        }
        return false;
    }

    public List<Friend> getAllFriendsByIncomeAndWasCustomer(Double income, Boolean wasCustomer) {
        return friendRepository.findAllByIncomeGreaterThanEqualAndWasCustomer(income, wasCustomer).stream()
                .sorted(Comparator.comparing(Friend::getIncome).reversed())
                .collect(Collectors.toList());
    }

    public List<Friend> getFriendsByIncome(Double income) {
        return friendRepository.findAllByIncomeGreaterThanEqual(income).stream()
               .sorted(Comparator.comparing(Friend::getIncome).reversed())
               .collect(Collectors.toList());
    }

    public List<Friend> getAllFriendsWasCustomer(Boolean wasCustomer) {
        return friendRepository.findAllByWasCustomer(wasCustomer).stream()
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


    public List<Friend> getAllFriendsByCategory(Category category) {
        return friendRepository.findAllByCategory(category);

    }
}
