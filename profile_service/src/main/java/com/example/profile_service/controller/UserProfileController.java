package com.example.profile_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.profile_service.dto.request.UserProfileCreationRequest;
import com.example.profile_service.dto.response.UserProfileResponse;
import com.example.profile_service.service.UserProfileService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping({"/user"})
public class UserProfileController {
    private final UserProfileService userProfileService;
    
    @GetMapping()
    public List<UserProfileResponse> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }
    
    
    @PostMapping({"", "/"})
    public UserProfileResponse createUserProfile(@RequestBody UserProfileCreationRequest request) {
        return userProfileService.createUserProfile(request);
    }
    
    @GetMapping({"/{userProfileId}", "/{userProfileId}/"})
    public UserProfileResponse getUserProfile(@PathVariable("userProfileId") String userProfileId) {
        return userProfileService.getUserProfile(userProfileId);
    }
    
}
