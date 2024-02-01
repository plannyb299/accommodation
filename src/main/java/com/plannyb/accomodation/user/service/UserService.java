package com.plannyb.accomodation.user.service;





import com.plannyb.accomodation.user.model.User;
import com.plannyb.accomodation.user.model.dto.UserDto;
import com.plannyb.accomodation.user.model.dto.UserPostDto;

import java.util.List;


public interface UserService {
    User findByUsername(String username);
    User findById(Long id);
    UserDto findDtoById(Long id);

    List<UserDto> findAll();
    UserDto save(UserDto userPostDto);
    UserDto save(UserPostDto userPostDto);

    void approve(Long id);

    void deleteById(Long id);

    List<UserDto> findUnapprovedUsers();
}
