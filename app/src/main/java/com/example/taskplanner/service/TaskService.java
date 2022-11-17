package com.example.taskplanner.service;

import com.example.taskplanner.dto.TaskDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TaskService {

    @GET("tasks")
    Call<List<TaskDto>> getTasks();
}
