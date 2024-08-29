package de.supercode.lohnt_er_sich.service;

import de.supercode.lohnt_er_sich.dto.FriendDTO;
import de.supercode.lohnt_er_sich.entity.Category;
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
    FriendRepository mockFriendRepository;

    @Mock
    FriendMapper mockFriendMapper;

    @Mock
    CategoryService mockCategoryService;

    @InjectMocks
    private FriendService mockFriendService;

    private Friend friend1;
    private Friend friend2;
    private Friend friend3;
    private Friend friend4;

    private FriendDTO friendDTO1;
    private FriendDTO friendDTO2;
    private FriendDTO friendDTO3;
    private FriendDTO friendDTO4;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockFriendService = new FriendService(mockFriendRepository, mockFriendMapper, mockCategoryService);

        friendDTO1 = new FriendDTO();
        friendDTO1.setId(1L);
        friendDTO1.setFirstname("John");
        friendDTO1.setLastname("Doe");
        friendDTO1.setBirthday(LocalDate.of(2000, 8, 28));
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
        friend1.setBirthday(LocalDate.of(2000, 8, 28));
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
        friendDTO2.setBirthday(LocalDate.of(2005, 2, 2));
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
        friend2.setBirthday(LocalDate.of(2005, 2, 2));
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
        friendDTO3.setBirthday(LocalDate.of(1995, 8, 28));
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
        friend3.setBirthday(LocalDate.of(1995, 8, 28));
        friend3.setPhone("1112223333");
        friend3.setEmail("alice.brown@example.com");
        friend3.setJob("Manager");
        friend3.setIncome(70000.00);
        friend3.setSelfEmployed(false);
        friend3.setWasCustomer(true);

        //----------
        friendDTO4 = new FriendDTO();
        friendDTO4.setId(4L);
        friendDTO4.setFirstname("Reiner");
        friendDTO4.setLastname("Zufall");
        friendDTO4.setBirthday(LocalDate.of(1980, 8, 28));
        friendDTO4.setPhone("55512345678");
        friendDTO4.setEmail("Reiner.Zufall@example.de");
        friendDTO4.setJob("Manager");
        friendDTO4.setIncome(100000.00);
        friendDTO4.setSelfEmployed(false);
        friendDTO4.setWasCustomer(true);

        friend4 = new Friend();
        friend4.setId(4L);
        friend4.setFirstname("Reiner");
        friend4.setLastname("Zufall");
        friend4.setBirthday(LocalDate.of(1980, 8, 28));
        friend4.setPhone("55512345678");
        friend4.setEmail("Reiner.Zufall@example.de");
        friend4.setJob("Manager");
        friend4.setIncome(100000.00);
        friend4.setSelfEmployed(false);
        friend4.setWasCustomer(true);
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

    @Test
    public void testGetAllFriendsByIncomeAndWasCustomer() {
        // Arrange
        Double income = 60000.00;
        Boolean wasCustomer = true;

        // Angenommen, das Repository gibt eine Liste von Freunden zurück, die die Kriterien erfüllen
        when(mockFriendRepository.findAllByIncomeGreaterThanEqualAndWasCustomer(income, wasCustomer))
                .thenReturn(List.of(friend3, friend4));

        // Act
        List<Friend> result = mockFriendService.getAllFriendsByIncomeAndWasCustomer(income, wasCustomer);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Reiner", result.get(0).getFirstname());
        assertEquals("Alice", result.get(1).getFirstname());

        // Verify, dass die Repository-Methode einmal aufgerufen wurde
        verify(mockFriendRepository, times(1)).findAllByIncomeGreaterThanEqualAndWasCustomer(income, wasCustomer);
    }

    @Test
    public void testGetAllFriendsByIncomeAndWasCustomer_NoMatch() {
        // Arrange
        Double income = 50000.00;
        Boolean wasCustomer = false;

        // Angenommen, das Repository gibt keine Freunde zurück, die die Kriterien erfüllen
        when(mockFriendRepository.findAllByIncomeGreaterThanEqualAndWasCustomer(income, wasCustomer))
                .thenReturn(List.of());

        // Act
        List<Friend> result = mockFriendService.getAllFriendsByIncomeAndWasCustomer(income, wasCustomer);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());

        // Verify, dass die Repository-Methode einmal aufgerufen wurde
        verify(mockFriendRepository, times(1)).findAllByIncomeGreaterThanEqualAndWasCustomer(income, wasCustomer);
    }

    @Test
    public void getFriendsByIncome() {
        Double income = 70000.00;
        when(mockFriendRepository.findAllByIncomeGreaterThanEqual(income)).thenReturn(List.of(friend3, friend4));

        List<Friend> result = mockFriendService.getFriendsByIncome(income);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Reiner", result.get(0).getFirstname());
        assertEquals("Alice", result.get(1).getFirstname());

        // Verify, dass die Repository-Methode einmal aufgerufen wurde
        verify(mockFriendRepository, times(1)).findAllByIncomeGreaterThanEqual(income);

    }

    @Test
    public void getFriendsByIncome_NoMatch() {
        Double income = 50000.00;
        when(mockFriendRepository.findAllByIncomeGreaterThanEqual(income)).thenReturn(List.of());

        List<Friend> result = mockFriendService.getFriendsByIncome(income);

        assertNotNull(result);
        assertEquals(0, result.size());

        // Verify, dass die Repository-Methode einmal aufgerufen wurde
        verify(mockFriendRepository, times(1)).findAllByIncomeGreaterThanEqual(income);

    }

    @Test
    public void testGetFriendsByWasCustomer() {
        boolean wasCustomer = true;
        when(mockFriendRepository.findAllByWasCustomer(wasCustomer)).thenReturn(List.of(friend1, friend2));

        List<Friend> result = mockFriendService.getAllFriendsWasCustomer(wasCustomer);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Jane", result.get(0).getFirstname());
        assertEquals("John", result.get(1).getFirstname());

        // Verify, dass die Repository-Methode einmal aufgerufen wurde
        verify(mockFriendRepository, times(1)).findAllByWasCustomer(wasCustomer);
    }


    @Test
    public void testGetFriendsByIsSelfEmployed() {
        boolean isSelfEmployed = true;
        when(mockFriendRepository.findAllBySelfEmployed(isSelfEmployed)).thenReturn(List.of(friend2));

        List<Friend> result = mockFriendService.getFriendsByIsSelfEmployed(isSelfEmployed);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Jane", result.get(0).getFirstname());

        // Verify, dass die Repository-Methode einmal aufgerufen wurde
        verify(mockFriendRepository, times(1)).findAllBySelfEmployed(isSelfEmployed);
    }

    @Test
    public void testGetFriendsByAge() {
        LocalDate now = LocalDate.of(2024, 8, 28);
        //LocalDate birthday1 = LocalDate.of(2000, 8, 28); // 24 Jahre alt
        //LocalDate birthday3 = LocalDate.of(1995, 8, 28); // 29 Jahre alt
        //LocalDate birthday4 = LocalDate.of(1980, 8, 28); // 44 Jahre alt



        when(mockFriendRepository.findAll()).thenReturn(List.of(friend1, friend2, friend3, friend4));

        List<Friend> result = mockFriendService.getFriendsByAge(24);

        assertEquals(2, result.size());
        assertEquals(friend3, result.get(0));
        assertEquals(friend4, result.get(1));
    }


}