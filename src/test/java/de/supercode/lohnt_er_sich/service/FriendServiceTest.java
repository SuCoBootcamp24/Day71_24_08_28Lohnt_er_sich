package de.supercode.lohnt_er_sich.service;

import de.supercode.lohnt_er_sich.entity.Friend;
import de.supercode.lohnt_er_sich.repository.FriendRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FriendServiceTest {

    @Mock
    private FriendRepository mockFriendRepository;

    @InjectMocks
    private FriendService mockFriendService;

    private Friend friend1;
    private Friend friend2;
    private Friend friend3;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock-Freunde erstellen
        friend1 = new Friend();
        friend1.setId(1L);
        friend1.setFirstname("John");
        friend1.setLastname("Doe");
        friend1.setBirthday(LocalDate.of(1990, 1, 1));
        friend1.setMobile("1234567890");
        friend1.setEmail("john.doe@example.com");
        friend1.setJob("Engineer");
        friend1.setIncome(50000.00);
        friend1.setSelfEmployed(false);
        friend1.setWasCustomer(true);

        friend2 = new Friend();
        friend2.setId(2L);
        friend2.setFirstname("Jane");
        friend2.setLastname("Smith");
        friend2.setBirthday(LocalDate.of(1992, 2, 2));
        friend2.setMobile("0987654321");
        friend2.setEmail("jane.smith@example.com");
        friend2.setJob("Designer");
        friend2.setIncome(60000.00);
        friend2.setSelfEmployed(true);
        friend2.setWasCustomer(false);

        friend3 = new Friend();
        friend3.setId(3L);
        friend3.setFirstname("Alice");
        friend3.setLastname("Brown");
        friend3.setBirthday(LocalDate.of(1985, 3, 3));
        friend3.setMobile("1112223333");
        friend3.setEmail("alice.brown@example.com");
        friend3.setJob("Manager");
        friend3.setIncome(70000.00);
        friend3.setSelfEmployed(false);
        friend3.setWasCustomer(true);
    }

    @Test
    public void testGetAllFriends() {
        // Arrange
        when(mockFriendRepository.findAll()).thenReturn(List.of(friend1, friend2, friend3));

        // Act
        List<Friend> result = mockFriendService.getAllFriends();

        // Assert
        assertEquals(3, result.size());
        assertEquals("John", result.get(0).getFirstname());
        assertEquals("Jane", result.get(1).getFirstname());
        assertEquals("Alice", result.get(2).getFirstname());

        // Verify
        verify(mockFriendRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllFriends_EmptyList() {
        // Arrange
        when(mockFriendRepository.findAll()).thenReturn(List.of());

        // Act
        List<Friend> result = mockFriendService.getAllFriends();

        // Assert
        assertEquals(0, result.size());

        // Verify
        verify(mockFriendRepository, times(1)).findAll();
    }




}