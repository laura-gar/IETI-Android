package com.example.taskplanner.service;

import com.example.taskplanner.dto.LoginDto;
import com.example.taskplanner.dto.TokenDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("auth")
    Call <TokenDto> login(@Body LoginDto loginDto);
}
