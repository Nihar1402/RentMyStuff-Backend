package com.Rent.service;

import com.Rent.dto.ProductDto;
import com.Rent.dto.UserProfile;
import com.Rent.models.Address;
import com.Rent.models.Product;
import com.Rent.models.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class UserServing {
    public UserProfile convertToUserDto(User user, Address address) throws IOException {
        UserProfile userProfile = new UserProfile();
        userProfile.setUseid(user.getId());
        userProfile.setUserName(user.getUserName());
        userProfile.setEmail(user.getEmail());
        userProfile.setPassword(user.getPassword());
        userProfile.setUserPhone(user.getUserPhone());

        Path imagePath = new ClassPathResource("static/img/" + user.getUserPic()).getFile().toPath();
        byte[] imageBytes = Files.readAllBytes(imagePath);

        userProfile.setUserPic(imageBytes);
        userProfile.setAdressid(address.getId());
            userProfile.setPincode(address.getPincode());
            userProfile.setCity(address.getCity());
            userProfile.setState(address.getState());
            userProfile.setUserAddress(address.getAddr());

        return userProfile;

    }
}
