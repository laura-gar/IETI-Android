package com.example.taskplanner.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskplanner.entities.TaskEntity;

import java.util.List;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Dao
@Module
@InstallIn(SingletonComponent.class)
public interface TaskDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(TaskEntity task);

    @Update
    void update(TaskEntity task);

    @Query("SELECT * from task_table ORDER By id Asc")
    LiveData<List<TaskEntity>> getStudent();

    @Query("DELETE from task_table")
    void deleteAll();
}
