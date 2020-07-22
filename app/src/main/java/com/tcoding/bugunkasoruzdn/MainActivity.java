package com.tcoding.bugunkasoruzdn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingAddButton;
    private RecyclerView rv;
    private DateAdapter adapter;
    private dateDatabaseHelper db;
    private ImageView dataNotFound;
    private ArrayList<String> dateIdArray, dateMonthArray, dateDayArray;


    public void init(){
        floatingAddButton = findViewById(R.id.floatingAddButton);
        rv = findViewById(R.id.rv);
        dataNotFound = findViewById(R.id.imageViewDataNotFound);
        db = new dateDatabaseHelper(this);
        dateIdArray = new ArrayList<>();
        dateMonthArray = new ArrayList<>();
        dateDayArray = new ArrayList<>();
        adapter = new DateAdapter(this, dateIdArray, dateMonthArray, dateDayArray);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setEnabled(true);
        rv.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddDateActivity.class));
            }
        });

        DisplayData();
    }

    private void DisplayData() {
        Cursor c = db.ShowData();
        if (c.getCount() == 0) {
            dataNotFound.setVisibility(View.VISIBLE);
        } else {
            dataNotFound.setVisibility(View.GONE);
            c.moveToLast();
            c.moveToNext();
            while (c.moveToPrevious()) {
                dateIdArray.add(c.getString(0));
                dateMonthArray.add(c.getString(1));
                dateDayArray.add(c.getString(2));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        Cursor c = null;
        c = db.ShowData();

        if (itemId == R.id.itemDelete) {
            if (c.getCount() != 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Tüm Kayıtlar Silinecek...");
                builder.setMessage("Tüm kayıtlar silinecek. Silmek istedinize emin misiniz ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.DeleteAll();
                        finish();
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                    }
                });
                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            } else
                Toast.makeText(this, "Silinecek Kayıt Bulunamadı", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
