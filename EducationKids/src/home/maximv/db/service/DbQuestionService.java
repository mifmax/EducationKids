package home.maximv.db.service;

import home.maximv.db.vo.Question;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbQuestionService {
    public static final String TABLE_NAME = "question";

    public static final String UID = "_id";

    public static final String QUESTION = "f_question";

    public static final String PID_MEN = "fk_pidMen";

    public static final String RATING = "f_rating";

    protected static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" + UID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "  + QUESTION + " VARCHAR(255), " + PID_MEN
            + " INTEGER, " + RATING + " INTEGER)";

    protected static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DbQuestionService(Context context) {
        super();
    }

    public void addQuestion(Question question, Context context) {
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUESTION, question.getQuestion());
        values.put(PID_MEN, question.getPidMen());
        values.put(RATING, question.getRating());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Question getQuestion(int id, Context context) {
        SQLiteDatabase db = new DbService(context).getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { UID, QUESTION, PID_MEN }, UID
                + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        
        Question question = new Question();
        question.setPid(cursor.getInt(0));
        question.setQuestion(cursor.getString(1));
        question.setPidMen(cursor.getInt(2));
        question.setRating(cursor.getInt(3));
        cursor.close();
        db.close();
        return question;
    }

    public List<Question> getAllQuestions(Context context) {
        List<Question> questionList = new ArrayList<Question>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setPid(Integer.parseInt(cursor.getString(0)));
                question.setQuestion(cursor.getString(1));
                question.setPidMen(cursor.getInt(2));
                question.setRating(cursor.getInt(3));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return questionList;
    }

    public int updateContact(Question question, Context context) {
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUESTION, question.getQuestion());
        values.put(PID_MEN, question.getPidMen());
        values.put(RATING, question.getRating());
        int result = db.update(TABLE_NAME, values, UID + " = ?", new String[] { String.valueOf(question.getPid()) });
        db.close();
        return result;
    }

    public void deleteQuestion(Question question, Context context) {
        SQLiteDatabase db = new DbService(context).getWritableDatabase();
        db.delete(TABLE_NAME, UID + " = ?", new String[] { String.valueOf(question.getPid()) });
        db.close();
    }

    public int getQuestionsCount(Context context) {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = new DbService(context).getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        db.close();
        return cursor.getCount();
    }
}
