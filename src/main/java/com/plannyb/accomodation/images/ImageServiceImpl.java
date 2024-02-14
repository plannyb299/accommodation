package com.plannyb.accomodation.images;

import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.host.repository.HouseRepository;
import com.plannyb.accomodation.user.model.User;
import com.plannyb.accomodation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final HouseRepository hostRepository;


    private final UserRepository userRepository;

//    @Override
//    @Transactional
//    public void saveImageFileToHome(String homeId, MultipartFile file) {
//        try {
//            House myHome = hostRepository.findById(homeId).get();
//
//            byte[] byteObjects = new byte[file.getBytes().length];
//
//            int i = 0;
//
//            for (byte b : file.getBytes()){
//                byteObjects[i++] = b;
//            }
//
//            myHome.setImage(byteObjects);
//
//            hostRepository.save(myHome);
//        } catch (IOException e) {
//            log.error("Error occurred in save image to home", e);
//        }
//    }
    @Override
    @Transactional
    public void saveImageFileToUser(String userId, MultipartFile file) {
        try {
            User user = userRepository.findById(userId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            user.setImage(byteObjects);

            userRepository.save(user);
        } catch (IOException e) {
            log.error("Error occurred in save image to user", e);
        }
    }
}