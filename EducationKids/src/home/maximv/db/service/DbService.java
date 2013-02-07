package home.maximv.db.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbService extends SQLiteOpenHelper  implements BaseColumns{
    private static final String DATABASE_NAME = "educationkids.db";
    private static final int DATABASE_VERSION = 1;

    public DbService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);    
        new DbJournalService(context);
        new DbLearnerService(context);
    
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void closeCon(DbService sqh, SQLiteDatabase sqdb){
        sqdb.close();
        sqh.close();
    }

}
