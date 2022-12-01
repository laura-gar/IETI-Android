package com.example.taskplanner.roomdatabase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taskplanner.dao.TaskDAO;
import com.example.taskplanner.entities.TaskEntity;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@Database(entities = {TaskEntity.class}, version = 1, exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase {
    public abstract TaskDAO studentDao();

    private static volatile TaskRoomDatabase taskRoomDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TaskRoomDatabase getDatabase(final Context context) {
        if (taskRoomDatabase == null) {
            synchronized (UserRoomDatabase.class) {
                if (taskRoomDatabase == null) {
                    taskRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                                    TaskRoomDatabase.class, "task_database")
                            .build();
                }
            }
        }
        return taskRoomDatabase;
    }
}
