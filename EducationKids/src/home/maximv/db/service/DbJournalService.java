package home.maximv.db.service;

import home.maximv.db.vo.Journal;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DbJournalService extends SQLiteOpenHelper  implements BaseColumns{
    private static final String DATABASE_NAME = "educationkids.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "journal";
    public static final String UID = "_id";
    public static final String KEY_LEARNER = "key_learner";
    public static final String DATE = "date";
    public static final String COURSE1 =  "course1" ;
    public static final String COURSE2 =  "course2" ;
    public static final String COURSE3 =  "course3" ;
    public static final String COURSE4 =  "course4" ;
    public static final String COURSE5 =  "course5" ;
    public static final String COURSE6 =  "course6" ;
    public static final String COURSE7 =  "course7" ;
    public static final String COURSE8 =  "course8" ;
    public static final String COURSE9 =  "course9" ;
    public static final String COURSE10 = "course10";
    public static final String COURSE11 = "course11";
    public static final String COURSE12 = "course12";
    public static final String COURSE13 = "course13";
    public static final String COURSE14 = "course14";

    protected static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +" FOREIGN KEY+ ( "+KEY_LEARNER + " ) REFERENCES "+DbLearnerService.TABLE_NAME+"("+DbLearnerService.UID+") "
            + DATE     + " DATE, " 
            + COURSE1  + " INTEGER, "    + COURSE2  + " INTEGER, " 
            + COURSE3  + " INTEGER,    " + COURSE4  + " INTEGER, "
            + COURSE5  + " INTEGER,    " + COURSE6  + " INTEGER, "
            + COURSE7  + " INTEGER,    " + COURSE8  + " INTEGER, "
            + COURSE9  + " INTEGER,    " + COURSE10 + " INTEGER, "
            + COURSE11 + " INTEGER,    " + COURSE12 + " INTEGER, "
            + COURSE13 + " INTEGER,    " + COURSE14 + " INTEGER )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

    public DbJournalService(Context context) {
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

        // Adding new journal
        void addjournal(Journal journal) {
            SQLiteDatabase db = this.getWritableDatabase();
     
            ContentValues values = new ContentValues();
            values.put(KEY_LEARNER, journal.getLearner());
            values.put(DATE, journal.getDate());
            values.put(COURSE1, journal.getCourse1());
            values.put(COURSE2, journal.getCourse2());
            values.put(COURSE3, journal.getCourse3());
            values.put(COURSE4, journal.getCourse4());
            values.put(COURSE5, journal.getCourse5());
            values.put(COURSE6, journal.getCourse6());
            values.put(COURSE7, journal.getCourse7());
            values.put(COURSE8, journal.getCourse8());
            values.put(COURSE9, journal.getCourse9());
            values.put(COURSE10, journal.getCourse10());
            values.put(COURSE11, journal.getCourse11());
            values.put(COURSE12, journal.getCourse12());
            values.put(COURSE13, journal.getCourse13());
            values.put(COURSE14, journal.getCourse14());
         
            // Inserting Row
            db.insert(TABLE_NAME, null, values);
            db.close(); // Closing database connection
        }
     
        // Getting single contact
        Journal getJournal(int id) {
            SQLiteDatabase db = this.getReadableDatabase();
     
            Cursor cursor = db.query(TABLE_NAME, new String[] { UID,
                    KEY_LEARNER, DATE,COURSE1,COURSE2,COURSE3,COURSE4,COURSE5,COURSE6,COURSE7,
                    COURSE8,COURSE9,COURSE10,COURSE11,COURSE12,COURSE13,COURSE14,}, UID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
     
            Journal journal = new Journal(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2),cursor.getString(3),
                    cursor.getString(4), cursor.getString(5),cursor.getString(6),
                    cursor.getString(7), cursor.getString(8),cursor.getString(9),
                    cursor.getString(10),cursor.getString(11),cursor.getString(12),
                    cursor.getString(13),cursor.getString(14),cursor.getString(15),
                    cursor.getString(16),cursor.getString(71));
            return journal;
        }
     
        // Getting All Contacts
        public List<Journal> getAllJournals() {
            List<Journal> journalList = new ArrayList<Journal>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_NAME;
     
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
     
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Journal journal = new Journal();
                    journal.setPid(Integer.parseInt(cursor.getString(0)));
                    journal.setLearner(cursor.getString(1));
                    journal.setDate(cursor.getString(1));
                    journal.setCourse1(cursor.getString(1));
                    journal.setCourse2(cursor.getString(1));
                    journal.setCourse3(cursor.getString(1));
                    journal.setCourse4(cursor.getString(1));
                    journal.setCourse5(cursor.getString(1));
                    journal.setCourse6(cursor.getString(1));
                    journal.setCourse7(cursor.getString(1));
                    journal.setCourse8(cursor.getString(1));
                    journal.setCourse9(cursor.getString(1));
                    journal.setCourse10(cursor.getString(1));
                    journal.setCourse11(cursor.getString(1));
                    journal.setCourse12(cursor.getString(1));
                    journal.setCourse13(cursor.getString(1));
                    journal.setCourse14(cursor.getString(1));

                    // Adding contact to list
                    journalList.add(journal);
                } while (cursor.moveToNext());
            }
     
            // return journal list
            return journalList;
        }
     
        // Updating single journal
        public int updateContact(Journal journal) {
            SQLiteDatabase db = this.getWritableDatabase();
     
            ContentValues values = new ContentValues();
            values.put(KEY_LEARNER, journal.getLearner());
            values.put(DATE, journal.getDate());
            values.put(COURSE1, journal.getCourse1());
            values.put(COURSE2, journal.getCourse2());
            values.put(COURSE3, journal.getCourse3());
            values.put(COURSE4, journal.getCourse4());
            values.put(COURSE5, journal.getCourse5());
            values.put(COURSE6, journal.getCourse6());
            values.put(COURSE7, journal.getCourse7());
            values.put(COURSE8, journal.getCourse8());
            values.put(COURSE9, journal.getCourse9());
            values.put(COURSE10, journal.getCourse10());
            values.put(COURSE11, journal.getCourse11());
            values.put(COURSE12, journal.getCourse12());
            values.put(COURSE13, journal.getCourse13());
            values.put(COURSE14, journal.getCourse14());
     
            // updating row
            return db.update(TABLE_NAME, values, UID + " = ?",
                    new String[] { String.valueOf(journal.getPid()) });
        }
     
        // Deleting single contact
        public void deleteContact(Journal journal) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, UID + " = ?",
                    new String[] { String.valueOf(journal.getPid()) });
            db.close();
        }
     
        // Getting contacts Count
        public int getJournalsCount() {
            String countQuery = "SELECT  * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();
     
            // return count
            return cursor.getCount();
        }
     
    }
