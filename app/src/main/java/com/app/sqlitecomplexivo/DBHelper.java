package com.app.sqlitecomplexivo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE 'tblproductos' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'name' varchar(20) NOT NULL, 'price'	varchar(30) NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS 'tblstudent'");
    }

    public void addStudent(String name, String email){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", email);

        this.getWritableDatabase().insertOrThrow("tblproductos","",values);
    }

    // displayAllStudents In ListView
    public ArrayList displayAllStudent()
    {
        ArrayList list = new ArrayList();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tblproductos",null);
        while(cursor.moveToNext())
        {
            list.add(cursor.getInt(0) + "," + cursor.getString(1) + "," + cursor.getString(2));
        }
        return list;
    }

    //displayAllStudents in Text View
    public void displayAllStudents(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tblproductos",null);
        while(cursor.moveToNext()){
            textView.append("\nID:\t" + cursor.getInt(0) + "\t\tName:\t" + cursor.getString(1) + "\t\tPrice:\t" + cursor.getString(2));
        }
    }

    // displayStudent In Spinner
    public ArrayList displayStudentInSpinner()
    {
        ArrayList list = new ArrayList();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT price FROM tblproductos",null);
        while(cursor.moveToNext())
        {
            list.add(cursor.getString(0));
        }
        return list;
    }

    //displayStudent By Email ID
    public ArrayList displayStudentsByEmail(String price)
    {
        ArrayList list = new ArrayList();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tblproductos WHERE price='"+price+"'",null);
        while(cursor.moveToNext())
        {
            list.add("\nID:\t" + cursor.getInt(0) + "\t\tName:\t" + cursor.getString(1) + "\t\tEmail:\t" + cursor.getString(2));
        }
        return list;
    }

    //displayStudent By Email ID For Edit
    public Cursor displayStudentsByEmailEdit(String price)
    {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tblproductos WHERE price='"+price+"'",null);
        return cursor;
    }

    //Update Student Details
    public void updateStudentByEmail(String name,String newPrice,String oldPrice){
        this.getWritableDatabase().execSQL("UPDATE tblproductos SET name='"+ name +"',price='" + newPrice + "' WHERE price='"+ oldPrice +"'");
    }

    //Delete Student
    public void deletStudent(String price){
        //this.getWritableDatabase().delete("tblStudent","email='"+email+"'",null);
        this.getWritableDatabase().execSQL("DELETE FROM tblproductos WHERE price='"+ price +"'");
    }
}