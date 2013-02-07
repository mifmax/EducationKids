package home.maximv.db.service;

import home.maximv.db.vo.Learner;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbLearnerService {
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
            + BORN_YEAR  + " DATE,    " + EMAIL       + " VARCHAR(255), "
            + PHONE_NUM  + " VARCHAR(255), " + SEX         + " BYTE       )";

    protected static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

    public DbLearnerService(Context context) {
        super();    
    }

          // Adding new learner
    public void setlearner(Learner learner,Context context) {
            SQLiteDatabase db = new DbService(context).getWritableDatabase();
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
    public Learner getLearner(int id,Context context) {
            SQLiteDatabase db = new DbService(context).getReadableDatabase();
     
            Cursor cursor = db.query(TABLE_NAME, new String[] { UID,
                    FIRST_NAME, MIDDLE_NAME,LAST_NAME,LOGIN,BORN_YEAR,EMAIL,PHONE_NUM,SEX}, UID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
     
            Learner learner = new Learner(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2),cursor.getString(3),
                    cursor.getString(4), cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getString(8));
            db.close();
            return learner;
        }
  
    // Getting single learner
public Learner getLearner(String login,Context context) {
        SQLiteDatabase db = new DbService(context).getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_NAME, new String[] { UID,
                FIRST_NAME, MIDDLE_NAME,LAST_NAME,PHONE_NUM,LOGIN,BORN_YEAR,EMAIL,SEX}, LOGIN + "=?",
                new String[] { String.valueOf(login) }, null, null, null, null);
        Learner learner = new Learner();
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
 
        learner = new Learner(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),
                cursor.getString(4), cursor.getString(5),cursor.getString(6),
                cursor.getString(7),cursor.getString(8));
        }
        db.close();
        
        return learner;
    }

        // Getting All learners
        public List<Learner> getAllLearners(Context context) {
            List<Learner> learnerList = new ArrayList<Learner>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_NAME;
     
            SQLiteDatabase db = new DbService(context).getWritableDatabase();
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
     
            db.close();
            return learnerList;
        }
     
        // Updating single learner
        public int updateContact(Learner learner,Context context) {
            SQLiteDatabase db = new DbService(context).getWritableDatabase();
     
            ContentValues values = new ContentValues();
            values.put(FIRST_NAME, learner.getFirstName());
            values.put(MIDDLE_NAME, learner.getMiddleName());
            values.put(LAST_NAME, learner.getLastName());
            values.put(LOGIN, learner.getLogin());
            values.put(BORN_YEAR, learner.getBornYear());
            values.put(EMAIL, learner.getEmail());
            values.put(PHONE_NUM, learner.getPhoneNumber());
            values.put(SEX, learner.getSex());
            int result = db.update(TABLE_NAME, values, UID + " = ?",
                    new String[] { String.valueOf(learner.getPid()) });
            db.close();
            return result;
        }
     
        // Deleting single learner
        public void deleteLerner(Learner learner,Context context) {
            SQLiteDatabase db = new DbService(context).getWritableDatabase();
            db.delete(TABLE_NAME, UID + " = ?",
                    new String[] { String.valueOf(learner.getPid()) });
            db.close();
        }
     
        // Getting learners Count
        public int getLearnersCount(Context context) {
            String countQuery = "SELECT  * FROM " + TABLE_NAME;
            SQLiteDatabase db = new DbService(context).getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();
     
            db.close();
            return cursor.getCount();
        }
     
    }
