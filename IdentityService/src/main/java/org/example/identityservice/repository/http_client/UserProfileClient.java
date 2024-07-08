package org.example.identityservice.repository.http_client;

import org.example.identityservice.configuration.AuthenticationRequestInterceptor;
import org.example.identityservice.dto.request.UserProfileCreationRequest;
import org.example.identityservice.dto.response.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "user-profile-service",
        url = "${app.services.userProfile}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface UserProfileClient {
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse createUserProfile(
            // @RequestHeader("Authorization") String token,
            @RequestBody UserProfileCreationRequest request);

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Object getUserProfile(@PathVariable String userId);
}
