package home.maximv.dbservices;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DbService extends SQLiteOpenHelper  implements BaseColumns{
    private static final String DATABASE_NAME = "EducationKids.db";
    private static final int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "learner";
    public static final String UID = "_id";
    public static final String FIRST_NAME = "first_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String LAST_NAME = "last_name";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FIRST_NAME + " VARCHAR(255), "+MIDDLE_NAME+" VARCHAR(255), "+LAST_NAME+ " VARCHAR(255));";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

    public DbService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Обновление базы данных с версии " + oldVersion
                + " до версии " + newVersion + ", которое удалит все старые данные");
        // Удаляем предыдущую таблицу при апгрейде
        db.execSQL(SQL_DELETE_ENTRIES);
        // Создаём новый экземпляр таблицы
        onCreate(db);

    }
    public static void closeCon(DbService sqh, SQLiteDatabase sqdb){
        // закрываем соединения с базой данных
        sqdb.close();
        sqh.close();
    }

}
