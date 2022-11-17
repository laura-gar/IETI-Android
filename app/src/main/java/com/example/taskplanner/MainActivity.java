package com.example.taskplanner;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.taskplanner.auth.AuthInterceptor;
import com.example.taskplanner.dto.TaskDto;
import com.example.taskplanner.service.TaskService;
import com.example.taskplanner.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsonTxtView = findViewById(R.id.taskview);
        getTask();
    }

    private void getTask() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ieti-tasks.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

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