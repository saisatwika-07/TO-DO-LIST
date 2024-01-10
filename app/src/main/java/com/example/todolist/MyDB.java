package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDB  extends SQLiteOpenHelper
{
    public static final String DB_NAME="TO_DO_LIST2";
    public static final String TABLE_NAME="TODOLIST";
    String qry="create table "+TABLE_NAME+" (ttitle Text,tdisc Text,prilevel Text,cat Text,status Text,duedate Text )";
    Context context;
    MyDB(Context context)
    {
        super(context,DB_NAME,null,1);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(qry);
        Toast.makeText(context,"Database created..",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(String tasktitle,String tdsc,String prilevel,String cat,String taskstatus,String duedate)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        String ROW1 = "INSERT INTO " + TABLE_NAME+" values('"+tasktitle+"', '"+tdsc+"', '"+ prilevel+"', '"+cat+"','"+taskstatus+"','"+duedate+"') ";
        database.execSQL(ROW1);

//        ContentValues contentValues=new ContentValues();
//        contentValues.put("tasktitle",tasktitle);
//        contentValues.put("tdsc",tdsc);
//        contentValues.put("prilevel",prilevel);
//        contentValues.put("cat",cat);
//        contentValues.put("taskstatus",taskstatus);
//        contentValues.put("duedate",duedate);
//        database.insert(TABLE_NAME,null,contentValues);
        Toast.makeText(context,"Record inserted..",Toast.LENGTH_LONG).show();
        database.close();


    }
}
