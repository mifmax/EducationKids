package home.maximv.db.service;

import home.maximv.db.vo.Journal;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbJournalService {
    public static final String TABLE_NAME = "journal";

    public static final String UID = "_id";

    public static final String KEY_LEARNER = "key_learner";

    public static final String DATE = "date";

    public static final String COURSE1 = "course1";

    public static final String COURSE2 = "course2";

    public static final String COURSE3 = "course3";

    public static final String COURSE4 = "course4";

    public static final String COURSE5 = "course5";

    public static final String COURSE6 = "course6";

    public static final String COURSE7 = "course7";

    public static final String COURSE8 = "course8";

    public static final String COURSE9 = "course9";

    public static final String COURSE10 = "course10";

    public static final String COURSE11 = "course11";

    public static final String COURSE12 = "course12";

    public static final String COURSE13 = "course13";

    public static final String COURSE14 = "course14";
    
    private DbLearnerService learnerservice;
    
    protected static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" + UID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_LEARNER + " INTEGER, " + DATE + " DATE, " + COURSE1
            + " INTEGER, " + COURSE2 + " INTEGER, " + COURSE3 + " INTEGER,    " + COURSE4 + " INTEGER, " + COURSE5
            + " INTEGER,    " + COURSE6 + " INTEGER, " + COURSE7 + " INTEGER,    " + COURSE8 + " INTEGER, " + COURSE9
            + " INTEGER,    " + COURSE10 + " INTEGER, " + COURSE11 + " INTEGER,    " + COURSE12 + " INTEGER, "
            + COURSE13 + " INTEGER,    " + COURSE14 + " INTEGER, FOREIGN KEY ( " + KEY_LEARNER + " ) REFERENCES "
            + DbLearnerService.TABLE_NAME + "(" + DbLearnerService.UID + "))";
            ;

    protected static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DbJournalService(Context context) {
        super();
    }

    public void addjournal(Journal journal,Context context) {
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LEARNER, journal.getLearnerId());
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

        db.insert(TABLE_NAME, null, values);
        db.close(); 
    }

    public Journal getJournal(int id,Context context) {
        SQLiteDatabase db = new DbService(context).getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { UID, KEY_LEARNER, DATE, COURSE1, COURSE2, COURSE3, COURSE4,
                COURSE5, COURSE6, COURSE7, COURSE8, COURSE9, COURSE10, COURSE11, COURSE12, COURSE13, COURSE14, }, UID
                + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Journal journal = new Journal(Integer.parseInt(cursor.getString(0)), cursor.getInt(1), cursor.getString(2),
                cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6),
                cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10),
                cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14),
                cursor.getInt(15), cursor.getInt(16));
        learnerservice = new DbLearnerService(context);
        journal.setLearner(learnerservice.getLearner(journal.getLearnerId(), context));
        cursor.close();
        db.close(); 
        return journal;
    }

    public List<Journal> getAllJournals(Context context) {
        List<Journal> journalList = new ArrayList<Journal>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Journal journal = new Journal();
                journal.setPid(Integer.parseInt(cursor.getString(0)));
                journal.setLearnerId(cursor.getInt(1));
                journal.setDate(cursor.getString(2));
                journal.setCourse1(cursor.getInt(3));
                journal.setCourse2(cursor.getInt(4));
                journal.setCourse3(cursor.getInt(5));
                journal.setCourse4(cursor.getInt(6));
                journal.setCourse5(cursor.getInt(7));
                journal.setCourse6(cursor.getInt(8));
                journal.setCourse7(cursor.getInt(9));
                journal.setCourse8(cursor.getInt(10));
                journal.setCourse9(cursor.getInt(11));
                journal.setCourse10(cursor.getInt(12));
                journal.setCourse11(cursor.getInt(13));
                journal.setCourse12(cursor.getInt(14));
                journal.setCourse13(cursor.getInt(15));
                journal.setCourse14(cursor.getInt(16));

                journalList.add(journal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close(); 
        return journalList;
    }

    public int updateContact(Journal journal,Context context) {
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LEARNER, journal.getLearnerId());
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
        int result = db.update(TABLE_NAME, values, UID + " = ?", new String[] { String.valueOf(journal.getPid()) });
        db.close(); 
        return result;
    }

    public void deleteContact(Journal journal,Context context) {
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        db.delete(TABLE_NAME, UID + " = ?", new String[] { String.valueOf(journal.getPid()) });
        db.close();
    }

    public int getJournalsCount(Context context) {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = new DbService(context).getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        db.close(); 
        return cursor.getCount();
    }

}
