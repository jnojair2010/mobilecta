package com.jair.rdc216.dao.sqlite.repositorio;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jair.rdc216.dao.sqlite.model.LoginModel;

@Database(entities = {LoginModel.class},version =1)
public abstract class  LoginDataBases extends RoomDatabase {

    public static LoginDataBases INSTANCE;

    public abstract  LoginSql login();

    public static LoginDataBases getDataBases(Context context){
        if( INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, LoginDataBases.class, "cta")
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            // db.execSQL(CREATE_TABLE_USUARIO);
                        }
                    })
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;
    }

    private static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {

        }
    };


}
