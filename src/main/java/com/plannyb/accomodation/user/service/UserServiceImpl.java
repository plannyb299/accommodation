package com.plannyb.accomodation.user.service;

import com.plannyb.accomodation.user.model.User;
import com.plannyb.accomodation.user.model.dto.UserDto;
import com.plannyb.accomodation.user.model.dto.UserPostDto;
import com.plannyb.accomodation.user.processor.UserProcessor;
import com.plannyb.accomodation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserProcessor userProcessor;
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserDto findDtoById(Long id){
        return UserProcessor.convertToDto(userRepository.findById(id).get());
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserProcessor::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findUnapprovedUsers() {
        List<UserDto> allUsers = userRepository.findAll()
                .stream()
                .map(UserProcessor::convertToDto)
                .collect(Collectors.toList());
        return allUsers.stream().filter(t->t.getApproved()==0).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User tempUser = findById(userDto.getId());

        User user = UserProcessor.convert(userDto);

//        user.setFirstName(userDto.getFirstname());
//        user.setLastName(userDto.getLastname());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(tempUser.getPassword());
//        user.setRoles(tempUser.getRoles());

        user = userRepository.save(user);

        return UserProcessor.convertToDto(user);
    }

    @Override
    public UserDto save(UserPostDto userPostDto) {
        User user1 = new User();
        BeanUtils.copyProperties(userPostDto,user1);
        User user = user1;

        user = userRepository.save(user);

        return UserProcessor.convertToDto(user);
    }

    @Override
    public void approve(Long id) {
        User searchedUser = findById(id);
        searchedUser.setApproved(1);
        userRepository.save(searchedUser);
    }
}
