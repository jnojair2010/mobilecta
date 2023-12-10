package com.jair.rdc216.dao.sqlite.repositorio;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jair.rdc216.dao.sqlite.model.ConsultorModel;
import com.jair.rdc216.dao.sqlite.model.LoginModel;

@Database(entities = {ConsultorModel.class},version =1)
public abstract class ConsultorDatabase extends RoomDatabase {

    public static ConsultorDatabase INSTANCE;

    public abstract ConsultorSql consultor();

    public static ConsultorDatabase getDataBases(Context context){
        if( INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, ConsultorDatabase.class, "cta")
                    .allowMainThreadQueries()
                    .addCallback(new RoomDatabase.Callback() {
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
