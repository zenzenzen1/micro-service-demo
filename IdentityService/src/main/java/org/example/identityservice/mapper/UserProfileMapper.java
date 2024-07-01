package org.example.identityservice.mapper;

import org.example.identityservice.dto.request.UserCreationRequest;
import org.example.identityservice.dto.request.UserProfileCreationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "userId", ignore = true)
    UserProfileCreationRequest toUserProfileCreationRequest(UserCreationRequest userCreationRequest);
}
