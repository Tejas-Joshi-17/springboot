package com.sarvatra.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {

    @JsonProperty(value = "success")
    private boolean success;

    @JsonProperty(value = "accessToken")
    private String accessToken;

    @JsonProperty(value = "refreshToken")
    private String refreshToken;

}
