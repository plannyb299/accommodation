package com.plannyb.accomodation.user.processor;


import com.plannyb.accomodation.user.model.User;
import com.plannyb.accomodation.user.model.dto.UserDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserProcessor {

    public static UserDto convertToDto(User user){

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        return userDto;
    }

    public static User convert(UserDto userDto){

       User user = new User();
        BeanUtils.copyProperties(userDto, user);

        return user;
    }
}
