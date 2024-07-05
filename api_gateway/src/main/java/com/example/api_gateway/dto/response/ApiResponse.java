package com.example.api_gateway.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    @Builder.Default
    private int code = 1000;

    private String message;
    private T result;
}
