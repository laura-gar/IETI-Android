package com.example.taskplanner.roomdatabase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taskplanner.dao.UserDAO;
import com.example.taskplanner.entities.UserEntity;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {
    public abstract UserDAO studentDao();

    private static volatile UserRoomDatabase userRoomDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static UserRoomDatabase getDatabase(final Context context) {
        if (userRoomDatabase == null) {
            synchronized (UserRoomDatabase.class) {
                if (userRoomDatabase == null) {
                    userRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                                    UserRoomDatabase.class, "user_database")
                            .build();
                }
            }
        }
        return userRoomDatabase;
    }
}
