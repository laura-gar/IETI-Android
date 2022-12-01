package com.example.taskplanner.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskplanner.entities.UserEntity;

import java.util.List;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Dao
@Module
@InstallIn(SingletonComponent.class)
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(UserEntity user);

    @Update
    void update(UserEntity user);

    @Query("SELECT * from user_table ORDER By id Asc")
    LiveData<List<UserEntity>> getStudent();

    @Query("DELETE from user_table")
    void deleteAll();
}
