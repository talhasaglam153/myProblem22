package com.tcoding.bugunkasoruzdn;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity {
    private RecyclerView rvLesson;
    private ImageView imageViewDataNotFoundLesson;
    private ArrayList<String> lessonIdArray , lessonNameArray , lessonSubjectArray , solvedProblemCountArray , gelenDateIdArray;
    private LessonDatabaseHelper ldb;
    private LessonAdapter adapter;
    private FloatingActionButton floatingAddButtonLesson;

    public void init(){
        ldb = new LessonDatabaseHelper(this);
        rvLesson = findViewById(R.id.rvLesson);
        imageViewDataNotFoundLesson = findViewById(R.id.imageViewDataNotFoundLesson);
        floatingAddButtonLesson = findViewById(R.id.floatingAddButtonLesson);
        lessonIdArray = new ArrayList<>();
        lessonNameArray = new ArrayList<>();
        lessonSubjectArray = new ArrayList<>();
        solvedProblemCountArray = new ArrayList<>();
        gelenDateIdArray = new ArrayList<>();
        adapter = new LessonAdapter(this,lessonIdArray,lessonNameArray,lessonSubjectArray,solvedProblemCountArray,gelenDateIdArray);
        rvLesson.setLayoutManager(new LinearLayoutManager(this));
        rvLesson.setEnabled(true);
        rvLesson.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        init();
        String gelenBaslik = getIntent().getStringExtra("date");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(gelenBaslik);


        floatingAddButtonLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent newIntent = new Intent(LessonActivity.this,AddLessonActivity.class);
               String gelenId = getIntent().getStringExtra("dateId");
               newIntent.putExtra("dateId",gelenId);
               startActivity(newIntent);
            }
        });
        GelenVeriyiGoruntule();
    }


    public void GelenVeriyiGoruntule(){
            Cursor c = ldb.ViewAllData(getIntent().getStringExtra("dateId"));
            if (c.getCount() == 0) {
                imageViewDataNotFoundLesson.setVisibility(View.VISIBLE);
            } else {
                imageViewDataNotFoundLesson.setVisibility(View.GONE);
                c.moveToLast();
                c.moveToNext();
                while (c.moveToPrevious()) {
                    lessonIdArray.add(c.getString(0));
                    lessonNameArray.add(c.getString(1));
                    lessonSubjectArray.add(c.getString(2));
                    solvedProblemCountArray.add(c.getString(3));
                    gelenDateIdArray.add(c.getString(4));
                }
            }

    }
}
