package com.cleanCut.tidy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cleanCut.tidy.database.dbConnector;
import com.example.tidy.R;
import com.cleanCut.tidy.adapter.CleanerAdapter;
import com.cleanCut.tidy.database.cleaner;

import java.util.ArrayList;

public class viewCleaners extends AppCompatActivity {

    dbConnector db;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    CleanerAdapter cleanerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cleaners);
        recyclerView = findViewById(R.id.rv);
        db = new dbConnector(this);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void displayData() {
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM cleaner", null);
        ArrayList<cleaner> workers = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            String nic = cursor.getString(2);
            String address = cursor.getString(2);
            String number = cursor.getString(3);
            String email = cursor.getString(4);
            String password = cursor.getString(5);
            String c_passowrd = cursor.getString(6);


            workers.add(new cleaner( id,name,nic,address, number,email,password,c_passowrd));
        }
        cursor.close();
        cleanerAdapter = new CleanerAdapter(this, R.layout.singledatafor_cleaners, workers, sqLiteDatabase);
        recyclerView.setAdapter(cleanerAdapter);
    }
}