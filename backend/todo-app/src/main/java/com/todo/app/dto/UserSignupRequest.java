package com.todo.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignupRequest {

    @ApiModelProperty(value = "Name of the user", required = true)
    @NonNull
    private String name;

    @ApiModelProperty(value = "Email Id", required = true)
    @NonNull
    private String email;

    @ApiModelProperty(value = "Password", required = true)
    @NonNull
    private String password;
}