package home.maximv.db.service;

import home.maximv.db.vo.Learner;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DbLearnerService extends SQLiteOpenHelper  implements BaseColumns{
    private static final String DATABASE_NAME = "educationkids.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "learner";
    public static final String UID = "_id";
    public static final String FIRST_NAME = "first_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String LAST_NAME = "last_name";
    public static final String LOGIN = "login";
    public static final String BORN_YEAR = "born_year";
    public static final String EMAIL = "email";
    public static final String PHONE_NUM = "phone";
    public static final String SEX = "sex";
    protected static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FIRST_NAME + " VARCHAR(255), " + MIDDLE_NAME + " VARCHAR(255), " 
            + LAST_NAME  + " VARCHAR(255), " + LOGIN       + " VARCHAR(255), " 
            + BORN_YEAR  + " DATE(255),    " + EMAIL       + " VARCHAR(255), "
            + PHONE_NUM  + " VARCHAR(255), " + SEX         + " BYTE()       )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

    public DbLearnerService(Context context) {
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

        // Adding new learner
        void addlearner(Learner learner) {
            SQLiteDatabase db = this.getWritableDatabase();
     
            ContentValues values = new ContentValues();
            values.put(FIRST_NAME, learner.getFirstName());
            values.put(MIDDLE_NAME, learner.getMiddleName());
            values.put(LAST_NAME, learner.getLastName());
            values.put(LOGIN, learner.getLogin());
            values.put(BORN_YEAR, learner.getBornYear());
            values.put(EMAIL, learner.getEmail());
            values.put(PHONE_NUM, learner.getPhoneNumber());
            values.put(SEX, learner.getSex());
         
            // Inserting Row
            db.insert(TABLE_NAME, null, values);
            db.close(); // Closing database connection
        }
     
        // Getting single learner
        Learner getLearner(int id) {
            SQLiteDatabase db = this.getReadableDatabase();
     
            Cursor cursor = db.query(TABLE_NAME, new String[] { UID,
                    FIRST_NAME, MIDDLE_NAME,LAST_NAME,LOGIN,BORN_YEAR,EMAIL,PHONE_NUM,SEX}, UID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
     
            Learner learner = new Learner(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2),cursor.getString(3),
                    cursor.getString(4), cursor.getString(5),cursor.getString(6),
                    cursor.getString(7));
            return learner;
        }
     
        // Getting All learners
        public List<Learner> getAllLearners() {
            List<Learner> learnerList = new ArrayList<Learner>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_NAME;
     
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
     
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Learner learner = new Learner();
                    learner.setPid(Integer.parseInt(cursor.getString(0)));
                    learner.setFirstName(cursor.getString(1));
                    learner.setMiddleName(cursor.getString(2));
                    learner.setLastName(cursor.getString(3));
                    learner.setLogin(cursor.getString(4));
                    learner.setBornYear(cursor.getString(5));
                    learner.setEmail(cursor.getString(6));
                    learner.setPhoneNumber(cursor.getString(7));
                    learner.setSex(cursor.getString(8));

                    // Adding learner to list
                    learnerList.add(learner);
                } while (cursor.moveToNext());
            }
     
            // return learner list
            return learnerList;
        }
     
        // Updating single learner
        public int updateContact(Learner learner) {
            SQLiteDatabase db = this.getWritableDatabase();
     
            ContentValues values = new ContentValues();
            values.put(FIRST_NAME, learner.getFirstName());
            values.put(MIDDLE_NAME, learner.getMiddleName());
            values.put(LAST_NAME, learner.getLastName());
            values.put(LOGIN, learner.getLogin());
            values.put(BORN_YEAR, learner.getBornYear());
            values.put(EMAIL, learner.getEmail());
            values.put(PHONE_NUM, learner.getPhoneNumber());
            values.put(SEX, learner.getSex());
     
            // updating row
            return db.update(TABLE_NAME, values, UID + " = ?",
                    new String[] { String.valueOf(learner.getPid()) });
        }
     
        // Deleting single learner
        public void deleteContact(Learner learner) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, UID + " = ?",
                    new String[] { String.valueOf(learner.getPid()) });
            db.close();
        }
     
        // Getting learners Count
        public int getLearnersCount() {
            String countQuery = "SELECT  * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();
     
            // return count
            return cursor.getCount();
        }
     
    }
