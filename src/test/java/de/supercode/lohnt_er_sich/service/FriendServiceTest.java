package de.supercode.lohnt_er_sich.service;

import de.supercode.lohnt_er_sich.dto.FriendDTO;
import de.supercode.lohnt_er_sich.entity.Friend;
import de.supercode.lohnt_er_sich.mapper.FriendMapper;
import de.supercode.lohnt_er_sich.repository.FriendRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FriendServiceTest {

    @Mock
    private FriendRepository mockFriendRepository;

    @Mock
    FriendMapper mockFriendMapper;

    @InjectMocks
    private FriendService mockFriendService;

    private Friend friend1;
    private Friend friend2;
    private Friend friend3;

    private FriendDTO friendDTO1;
    private FriendDTO friendDTO2;
    private FriendDTO friendDTO3;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockFriendService = new FriendService(mockFriendRepository, mockFriendMapper);

        friendDTO1 = new FriendDTO();
        friendDTO1.setId(1L);
        friendDTO1.setFirstname("John");
        friendDTO1.setLastname("Doe");
        friendDTO1.setBirthday(LocalDate.of(1990, 1, 1));
        friendDTO1.setPhone("1234567890");
        friendDTO1.setEmail("john.doe@example.com");
        friendDTO1.setJob("Engineer");
        friendDTO1.setIncome(50000.00);
        friendDTO1.setSelfEmployed(false);
        friendDTO1.setWasCustomer(true);

        friend1 = new Friend();
        friend1.setId(1L);
        friend1.setFirstname("John");
        friend1.setLastname("Doe");
        friend1.setBirthday(LocalDate.of(1990, 1, 1));
        friend1.setPhone("1234567890");
        friend1.setEmail("john.doe@example.com");
        friend1.setJob("Engineer");
        friend1.setIncome(50000.00);
        friend1.setSelfEmployed(false);
        friend1.setWasCustomer(true);

        //----------
        friendDTO2 = new FriendDTO();
        friendDTO2.setId(2L);
        friendDTO2.setFirstname("Jane");
        friendDTO2.setLastname("Smith");
        friendDTO2.setBirthday(LocalDate.of(1992, 2, 2));
        friendDTO2.setPhone("0987654321");
        friendDTO2.setEmail("jane.smith@example.com");
        friendDTO2.setJob("Designer");
        friendDTO2.setIncome(60000.00);
        friendDTO2.setSelfEmployed(true);
        friendDTO2.setWasCustomer(false);

        friend2 = new Friend();
        friend2.setId(2L);
        friend2.setFirstname("Jane");
        friend2.setLastname("Smith");
        friend2.setBirthday(LocalDate.of(1992, 2, 2));
        friend2.setPhone("0987654321");
        friend2.setEmail("jane.smith@example.com");
        friend2.setJob("Designer");
        friend2.setIncome(60000.00);
        friend2.setSelfEmployed(true);
        friend2.setWasCustomer(false);


        //----------
        friendDTO3 = new FriendDTO();
        friendDTO3.setId(3L);
        friendDTO3.setFirstname("Alice");
        friendDTO3.setLastname("Brown");
        friendDTO3.setBirthday(LocalDate.of(1985, 3, 3));
        friendDTO3.setPhone("1112223333");
        friendDTO3.setEmail("alice.brown@example.com");
        friendDTO3.setJob("Manager");
        friendDTO3.setIncome(70000.00);
        friendDTO3.setSelfEmployed(false);
        friendDTO3.setWasCustomer(true);

        friend3 = new Friend();
        friend3.setId(3L);
        friend3.setFirstname("Alice");
        friend3.setLastname("Brown");
        friend3.setBirthday(LocalDate.of(1985, 3, 3));
        friend3.setPhone("1112223333");
        friend3.setEmail("alice.brown@example.com");
        friend3.setJob("Manager");
        friend3.setIncome(70000.00);
        friend3.setSelfEmployed(false);
        friend3.setWasCustomer(true);
    }

    @Test
    public void testGetFriend() {
        when(mockFriendRepository.findById(anyLong())).thenReturn(Optional.of(friend1));

        Friend result = mockFriendService.getFriend(1L);

        assertEquals(friend1.getId(), result.getId());
        assertEquals(friend1.getFirstname(), result.getFirstname());
        assertEquals(friend1.getLastname(), result.getLastname());

        verify(mockFriendRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetFriend_NotExisting() {
        when(mockFriendRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertNull(mockFriendService.getFriend(1L));

        verify(mockFriendRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetAllFriends() {

        when(mockFriendRepository.findAll()).thenReturn(List.of(friend1, friend2, friend3));

        List<Friend> result = mockFriendService.getAllFriends();

        assertEquals(3, result.size());
        assertEquals("John", result.get(0).getFirstname());
        assertEquals("Jane", result.get(1).getFirstname());
        assertEquals("Alice", result.get(2).getFirstname());

        verify(mockFriendRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllFriends_EmptyList() {

        when(mockFriendRepository.findAll()).thenReturn(List.of());

        List<Friend> result = mockFriendService.getAllFriends();

        assertEquals(0, result.size());

        verify(mockFriendRepository, times(1)).findAll();
    }

    @Test
    public void testCreateFriend_NewFriend() {
        when(mockFriendRepository.findByFirstnameAndLastname(friendDTO1.getFirstname(), friendDTO1.getLastname())).thenReturn(Optional.empty());
        when(mockFriendMapper.toEntity(any(FriendDTO.class))).thenReturn(friend1);
        when(mockFriendRepository.save(any(Friend.class))).thenReturn(friend1);

        Friend result = mockFriendService.createFriend(friendDTO1);

        assertEquals(friend1.getFirstname(), result.getFirstname());
        assertEquals(friend1.getLastname(), result.getLastname());
        assertEquals(friend1.getBirthday(), result.getBirthday());
        assertEquals(friend1.getPhone(), result.getPhone());
        assertEquals(friend1.getEmail(), result.getEmail());
        assertEquals(friend1.getJob(), result.getJob());
        assertEquals(friend1.getIncome(), result.getIncome());
        assertEquals(friend1.isSelfEmployed(), result.isSelfEmployed());
        assertEquals(friend1.isWasCustomer(), result.isWasCustomer());

        verify(mockFriendRepository, times(1)).save(any(Friend.class));
    }

    @Test
    public void testCreateFriend_WithExistFriend() {
        when(mockFriendRepository.findByFirstnameAndLastname(friendDTO1.getFirstname(), friendDTO1.getLastname())).thenReturn(Optional.of(friend1));

        Friend result = mockFriendService.createFriend(friendDTO1);

        assertEquals(null, result);
        verify(mockFriendRepository, never()).save(any(Friend.class));
    }


    @Test
    public void testUpdateFriend() {
        Friend updatedFriend = friend1;
        updatedFriend.setIncome(90000.00);
        updatedFriend.setPhone("5551112223");
        when(mockFriendRepository.findById(friendDTO1.getId())).thenReturn(Optional.of(friend1));
        when(mockFriendMapper.toEntity(any(FriendDTO.class))).thenReturn(friend1);
        when(mockFriendRepository.save(any(Friend.class))).thenReturn(friend1);

        Friend result = mockFriendService.updateFriend(friendDTO1);

        assertEquals(friend1.getId(), result.getId());
        assertEquals(updatedFriend.getFirstname(), result.getFirstname());
        assertEquals(updatedFriend.getLastname(), result.getLastname());
        assertEquals(updatedFriend.getBirthday(), result.getBirthday());
        assertEquals(updatedFriend.getPhone(), result.getPhone());
        assertEquals(updatedFriend.getEmail(), result.getEmail());
        assertEquals(updatedFriend.getJob(), result.getJob());
        assertEquals(updatedFriend.getIncome(), result.getIncome());
        assertEquals(updatedFriend.isSelfEmployed(), result.isSelfEmployed());
    }

    @Test
    public void testUpdateFriend_IfFriendNotExist() {
        when(mockFriendRepository.findById(friendDTO1.getId())).thenReturn(Optional.empty());

        Friend result = mockFriendService.updateFriend(friendDTO1);

        assertNull(result);
    }

    @Test
    public void testDeleteFriend_ExistFriend() {
        when(mockFriendRepository.findById(friendDTO3.getId())).thenReturn(Optional.of(friend3));

        boolean result = mockFriendService.deleteFriend(friendDTO3.getId());

        assertTrue(result);
    }

    @Test
    public void testDeleteFriend_FriendNotFind() {
        when(mockFriendRepository.findById(friendDTO3.getId())).thenReturn(Optional.empty());

        boolean result = mockFriendService.deleteFriend(friendDTO3.getId());

        assertFalse(result);
    }

}