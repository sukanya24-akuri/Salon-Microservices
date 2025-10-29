package com.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salon
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String name;
    private  String address;
    private  String city;
    private  String phoneNum;
    @NotBlank(message = "email is mandatory")
    private  String email;
    private Long ownerId;
    @ElementCollection
    private List<String> images;
    private LocalTime openAt;
    private LocalTime closeAt;



}
