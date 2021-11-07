package com.android.radiobuttons;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RadioButton radioButton,radioButton2;
    Button button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dBmain=new DBmain(MainActivity.this);
        findid();
        insertData();
    }

    private void insertData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues=new ContentValues();
                if (radioButton.isChecked()){
                    Toast.makeText(MainActivity.this, "check", Toast.LENGTH_SHORT).show();
                    contentValues.put("gender",radioButton.getText().toString());

                }else {
                    contentValues.put("gender",radioButton2.getText().toString());
                }
                sqLiteDatabase=dBmain.getWritableDatabase();
                Long rec=sqLiteDatabase.insert("studenttable",null,contentValues);
                if (rec!=null){
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();

                }else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Data not Inserted");
                    builder.setPositiveButton("OK",null);
                    builder.setCancelable(true);
                    builder.show();
                }
            }
        });
        //display dta when click display button
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DisplayData.class);
                startActivity(intent);
            }
        });
    }

    private void findid() {
        radioButton=(RadioButton)findViewById(R.id.radioButton);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
    }
}