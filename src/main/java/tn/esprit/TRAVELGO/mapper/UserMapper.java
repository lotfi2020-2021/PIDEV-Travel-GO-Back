package tn.esprit.TRAVELGO.mapper;
  


import org.springframework.stereotype.Component;

import tn.esprit.TRAVELGO.Dto.UserDto;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.entities.UserDetails;

@Component
public class UserMapper {

    public UserDto toDto(User user, UserDetails userDetails) {

    	 return new UserDto(
                 user.getId(),
                 user.getEmail(),
                 userDetails.getUsername(),
                 userDetails.getPhoneNumberUser()
         );
     }
}
