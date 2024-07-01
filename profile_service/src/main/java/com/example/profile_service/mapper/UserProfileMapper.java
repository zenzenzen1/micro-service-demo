package com.example.profile_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.profile_service.dto.request.UserProfileCreationRequest;
import com.example.profile_service.dto.response.UserProfileResponse;
import com.example.profile_service.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "id", ignore = true)
    UserProfile toUserProfile(UserProfileCreationRequest userProfileCreationRequest);
    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
}
