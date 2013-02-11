package home.maximv.db.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DbService extends SQLiteOpenHelper  implements BaseColumns{
    protected static final String DATABASE_NAME = "educationkids.db";
    protected static final int DATABASE_VERSION = 1;

    public DbService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);    
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbLearnerService.SQL_CREATE_ENTRIES);
        db.execSQL(DbJournalService.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Обновление базы данных с версии " + oldVersion
                + " до версии " + newVersion + ", которое удалит все старые данные");
        // Удаляем предыдущую таблицу при апгрейде
        db.execSQL(DbLearnerService.SQL_DELETE_ENTRIES);
        db.execSQL(DbJournalService.SQL_DELETE_ENTRIES);
        // Создаём новый экземпляр таблицы
        onCreate(db);
    }
}
