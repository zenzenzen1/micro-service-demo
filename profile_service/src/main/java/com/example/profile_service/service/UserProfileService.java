package com.example.profile_service.service;

import org.springframework.stereotype.Service;

import com.example.profile_service.dto.request.UserProfileCreationRequest;
import com.example.profile_service.dto.response.UserProfileResponse;
import com.example.profile_service.entity.UserProfile;
import com.example.profile_service.mapper.UserProfileMapper;
import com.example.profile_service.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    
    public UserProfileResponse createUserProfile(UserProfileCreationRequest userProfileCreationRequest) {
        UserProfile userProfile = userProfileRepository.save(userProfileMapper.toUserProfile(userProfileCreationRequest));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }
    
    public UserProfileResponse getUserProfile(String userId) {
        UserProfile userProfile = userProfileRepository.findById(userId).orElseThrow(() -> new RuntimeException("User profile not found"));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }
}
