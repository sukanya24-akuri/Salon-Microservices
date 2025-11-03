package com.spring.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
public class UserDTO {
    private Long userId;
    private String fullName;
    private String email;


}
