package com.plannyb.accomodation.images;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFileToHome(String homeId, MultipartFile file);
    void saveImageFileToUser(String userId, MultipartFile file);
}
