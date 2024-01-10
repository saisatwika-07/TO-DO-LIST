package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Result extends AppCompatActivity {
    TextView result;
    Button show;
    String task;
    MyDB myDB=new MyDB(Result.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result=findViewById(R.id.result);
        show=findViewById(R.id.show);
        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        task=bundle.getString("res");
        result.setText(task);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"title",Toast.LENGTH_LONG).show();

                String qry="select * from "+MyDB.TABLE_NAME;
                SQLiteDatabase database=myDB.getReadableDatabase();
                Cursor cursor=database.rawQuery(qry,null);
                ArrayList<String> mylist
                        = new ArrayList<>();

                // moving our cursor to first position.
                if (cursor.moveToFirst()) {
                    do {
                        // on below line we are adding the data from
                        // cursor to our array list.
                        mylist.add(
                                cursor.getString(1)
                                );
                    } while (cursor.moveToNext());
                    // moving our cursor to next.
                }
                Toast.makeText(getApplicationContext(),mylist.toString(),Toast.LENGTH_LONG).show();


            }
        });

    }
}