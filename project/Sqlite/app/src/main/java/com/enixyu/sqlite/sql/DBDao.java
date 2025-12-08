package com.enixyu.sqlite.sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.enixyu.sqlite.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DBDao {

    public static final String TABLE_NAME = "student";

    private static final String ID = "id";
    private static final String NAME = "stu_name";
    private static final String AGE = "stu_age";
    private static final String SEX = "stu_sex";
    private static final String GRADE = "stu_grade";
    private static final String STORE = "store";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME + " TEXT," +
            AGE + " INTEGER," +
            SEX + " TEXT," +
            GRADE + " TEXT," +
            STORE + " TEXT" +
            ")";

    private static volatile DBDao instance;  // ✅ 加volatile防止指令重排
    private DBHelper dbHelper;

    /** 双重检查锁定 */
    public static DBDao getInstance(Context context) {
        if (instance == null) {                     // 第一次检查（不加锁）
            synchronized (DBDao.class) {            // 加锁
                if (instance == null) {             // 第二次检查（加锁后）
                    instance = new DBDao(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    /** 私有构造函数 */
    private DBDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    /** 插入数据 */
    public void insert(Student student) {
        if (student == null) return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put(NAME, student.getName());
            cv.put(AGE, student.getAge());
            cv.put(SEX, student.getSex());
            cv.put(GRADE, student.getGrade());
            cv.put(STORE, student.getStore());

            db.insert(TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /** 删除表中所有数据 */
    public void clearAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_NAME, "id = ?", new String[]{"1"});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /** 查询所有数据 */
    @SuppressLint("Range")
    public List<Student> query() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {

                    cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Student student = new Student();
                student.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                student.setAge(cursor.getInt(cursor.getColumnIndex(AGE)));
                student.setSex(cursor.getString(cursor.getColumnIndex(SEX)));
                student.setGrade(cursor.getString(cursor.getColumnIndex(GRADE)));
                student.setStore(cursor.getString(cursor.getColumnIndex(STORE)));
                list.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return list;
    }


    /** 获取第一条数据的ID */
    @SuppressLint("Range")
    public int getFirstStudentId() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, new String[]{ID}, null, null, null, null, ID + " ASC", "1");
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getInt(cursor.getColumnIndex(ID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return -1;
    }


    /** 根据ID查询学生 */
    @SuppressLint("Range")
    public Student getStudentById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, null, ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                student.setAge(cursor.getInt(cursor.getColumnIndex(AGE)));
                student.setSex(cursor.getString(cursor.getColumnIndex(SEX)));
                student.setGrade(cursor.getString(cursor.getColumnIndex(GRADE)));
                student.setStore(cursor.getString(cursor.getColumnIndex(STORE)));
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return null;
    }

    /** 修改学生数据 */
    public boolean updateStudent(Student student) {
        if (student == null) return false;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put(NAME, student.getName());
            cv.put(AGE, student.getAge());
            cv.put(SEX, student.getSex());
            cv.put(GRADE, student.getGrade());
            cv.put(STORE, student.getStore());

            // 假设我们通过ID来更新，这里需要先获取第一条数据的ID
            String whereClause = ID + "=?";
            String[] whereArgs = {String.valueOf(student.getId())};

            int rowsAffected = db.update(TABLE_NAME, cv, whereClause, whereArgs);
            db.setTransactionSuccessful();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /** 查询数据库中的表名 */
    public List<String> tablesInDB() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

            while (cursor.moveToNext()) {
                list.add(cursor.getString(0));
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return list;
    }

    /** 获取数据库文件列表 */
    public String[] databaseList(Context context) {
        return context.databaseList();
    }

    /** 关闭数据库连接（在App退出时调用） */
    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}
