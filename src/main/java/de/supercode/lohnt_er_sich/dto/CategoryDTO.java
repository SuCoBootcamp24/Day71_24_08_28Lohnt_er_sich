package de.supercode.lohnt_er_sich.dto;

import de.supercode.lohnt_er_sich.entity.Friend;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class CategoryDTO {

    private long id;

    private String name;

    private List<Friend> friendList ;
}


