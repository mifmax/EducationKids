package home.maximv.db.service;

import home.maximv.db.vo.Answer;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAnswerService {
    public static final String TABLE_NAME = "answer";

    public static final String UID = "_id";

    public static final String KEY_QUESTION = "fk_question";

    public static final String ANSWER = "f_answer";

    public static final String PID_MEN = "fk_pidMen";

    public static final String RATING = "f_rating";

    protected static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" + UID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ANSWER + " VARCHAR(255), " + PID_MEN
            + " INTEGER, " + RATING + " INTEGER, "+KEY_QUESTION+ " INTEGER, FOREIGN KEY ( " + KEY_QUESTION + " ) REFERENCES "
            + DbQuestionService.TABLE_NAME + "(" + DbQuestionService.UID + "))";

    protected static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DbAnswerService(Context context) {
        super();
    }

    public void addAnswer(Answer answer, Context context) {
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, answer.getQuestionId());
        values.put(ANSWER, answer.getAnswer());
        values.put(PID_MEN, answer.getPidMen());
        values.put(RATING, answer.getRating());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Answer getAnswer(int id, Context context) {
        SQLiteDatabase db = new DbService(context).getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { UID, ANSWER, PID_MEN, KEY_QUESTION }, UID
                + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        
        Answer answer = new Answer();
        answer.setPid(cursor.getInt(0));
        answer.setAnswer(cursor.getString(1));
        answer.setPidMen(cursor.getInt(2));
        answer.setRating(cursor.getInt(3));
        answer.setQuestionId(cursor.getInt(4));
        cursor.close();
        db.close();
        return answer;
    }

    public List<Answer> getAllAnswers(Context context) {
        List<Answer> answerList = new ArrayList<Answer>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Answer answer = new Answer();
                answer.setPid(Integer.parseInt(cursor.getString(0)));
                answer.setAnswer(cursor.getString(1));
                answer.setPidMen(cursor.getInt(2));
                answer.setRating(cursor.getInt(3));
                answer.setQuestionId(cursor.getInt(4));
                answerList.add(answer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return answerList;
    }

    public int updateContact(Answer answer, Context context) {
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, answer.getQuestionId());
        values.put(ANSWER, answer.getAnswer());
        values.put(PID_MEN, answer.getPidMen());
        values.put(RATING, answer.getRating());
        int result = db.update(TABLE_NAME, values, UID + " = ?", new String[] { String.valueOf(answer.getPid()) });
        db.close();
        return result;
    }

    public void deleteAnswer(Answer answer, Context context) {
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        db.delete(TABLE_NAME, UID + " = ?", new String[] { String.valueOf(answer.getPid()) });
        db.close();
    }

    public int getAnswersCount(Context context) {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = new DbService(context).getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        db.close();
        return cursor.getCount();
    }

}
