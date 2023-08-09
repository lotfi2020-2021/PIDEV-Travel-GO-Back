package tn.esprit.TRAVELGO.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private final Long userId;
    private final String email;
    private final String username;
    private final String phoneNumberUser;
    
}
