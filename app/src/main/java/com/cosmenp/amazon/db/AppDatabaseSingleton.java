package com.cosmenp.amazon.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

public class AppDatabaseSingleton {
    private static AppDatabaseSingleton appDatabeSingleton;
    private AppDatabase appDatabase;

    public static AppDatabaseSingleton getInstance(Context context) {
        if (appDatabeSingleton == null) {
            appDatabeSingleton = new AppDatabaseSingleton(context);
        }
        return appDatabeSingleton;
    }

    private AppDatabaseSingleton(final Context context) {
       Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase = Room.databaseBuilder(context, AppDatabase.class, "dbamazon")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        });
       a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
