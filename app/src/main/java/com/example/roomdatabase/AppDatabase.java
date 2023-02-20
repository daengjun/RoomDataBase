package com.example.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static AppDatabase INSTANCE;

    //디비객체생성 가져오기
    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "database-name")
                                    .fallbackToDestructiveMigration() //스키마 버전 변경 가능
                    .allowMainThreadQueries()
                    .build();

            /*INSTANCE = Room.databaseBuilder(context, TodoDatabase.class , "todo-db")
                    .allowMainThreadQueries() =>이걸 추가해서 AsyncTask를 사용안하고 간편하게할수있지만 오류가많아 실제 앱을 만들때 사용하면 안된다고한다.
                    .build();*/
        }
        return INSTANCE;
    }


}