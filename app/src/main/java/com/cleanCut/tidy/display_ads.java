package com.cleanCut.tidy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.cleanCut.tidy.database.adz;
import com.cleanCut.tidy.database.dbConnector;
import com.example.tidy.R;
import com.cleanCut.tidy.adapter.AddsAdapter;

import java.util.ArrayList;

public class display_ads extends AppCompatActivity {

    dbConnector db;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    AddsAdapter constructionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ads);

        recyclerView = findViewById(R.id.rv);
        db = new dbConnector(this);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

    }

    private void displayData() {

        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Adz", null);
        ArrayList<adz> adds = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String room = cursor.getString(1);
            String bathroom = cursor.getString(2);
            String flooring = cursor.getString(3);
            String storeys = cursor.getString(4);
            String location = cursor.getString(5);
            String contact = cursor.getString(6);
            String price = cursor.getString(7);
            byte[] img = cursor.getBlob(8);


            adds.add(new adz (id, room, bathroom, storeys, location, flooring, contact, price, img));
        }
        cursor.close();
        constructionAdapter = new AddsAdapter(this, adds, sqLiteDatabase, R.layout.singledatafor_adds);
        recyclerView.setAdapter(constructionAdapter);

    }
}