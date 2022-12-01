package com.example.taskplanner;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.taskplanner.dao.TaskDAO;
import com.example.taskplanner.dao.TaskDAO_Impl;
import com.example.taskplanner.dao.UserDAO;
import com.example.taskplanner.dto.TaskDto;
import com.example.taskplanner.entities.TaskEntity;
import com.example.taskplanner.service.TaskService;

import org.modelmapper.ModelMapper;

import java.util.List;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Inject
    Retrofit retrofit;

    @Inject
    UserDAO userDAO;

    @Inject
    TaskDAO taskDAO;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsonTxtView = findViewById(R.id.taskview);
        getTask();

    }

    private void getTask() {
        TaskService taskService = retrofit.create(TaskService.class);
        Call<List<TaskDto>> call = taskService.getTasks();
        call.enqueue(new Callback<List<TaskDto>>() {
            @Override
            public void onResponse(Call<List<TaskDto>> call, Response<List<TaskDto>> response) {
                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Code: " + response.code());
                    return;
                }

                List<TaskDto> tasksList = response.body();
                for(TaskDto taskDto: tasksList ){
                taskDAO.insert(modelMapper.map(taskDto, TaskEntity.class));
                    String content = "";
                    content += "Id:" + taskDto.getId() + "\n";
                    content += "Name:" + taskDto.getName() + "\n";
                    content += "Description:" + taskDto.getDescription() + "\n";
                    content += "Status" + taskDto.getStatus() + "\n";
                    content += "DueDate" + taskDto.getDueDate() + "\n";

                    mJsonTxtView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<TaskDto>> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());

            }
        });
    }

}