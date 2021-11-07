package com.android.radiobuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayData extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    String[]name;
    int[]id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        dBmain=new DBmain(DisplayData.this);
        findid();
        displayData();
    }
    private void displayData() {
        sqLiteDatabase=dBmain.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select *from studenttable",null);
        if (cursor.getCount()>0){
            id=new int[cursor.getCount()];
            name=new String[cursor.getCount()];
            int i=0;
            while (cursor.moveToNext()){
                id[i]=cursor.getInt(0);
                name[i]=cursor.getString(1);
                i++;
            }
            Custom custom=new Custom();
            listView.setAdapter(custom);
        }
    }
    private void findid() {
        listView=(ListView)findViewById(R.id.lv);
    }
    private class Custom extends BaseAdapter {
        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            convertView= LayoutInflater.from(DisplayData.this).inflate(R.layout.singledata,parent,false);
            textView=convertView.findViewById(R.id.displayname);
            textView.setText(name[position]);
            return convertView;
        }
    }
}