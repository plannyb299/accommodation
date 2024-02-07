package com.plannyb.accomodation.user.service;





import com.plannyb.accomodation.user.model.User;
import com.plannyb.accomodation.user.model.dto.UserDto;
import com.plannyb.accomodation.user.model.dto.UserPostDto;

import java.util.List;


public interface UserService {
    User findByUsername(String username);
    User findById(String id);
    UserDto findDtoById(String id);

    List<UserDto> findAll();
    UserDto save(UserDto userPostDto);
    UserDto save(UserPostDto userPostDto);

    void approve(String id);

    void deleteById(String id);

    List<UserDto> findUnapprovedUsers();
}
