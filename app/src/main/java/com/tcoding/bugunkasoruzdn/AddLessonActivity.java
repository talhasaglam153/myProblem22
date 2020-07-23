package com.tcoding.bugunkasoruzdn;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddLessonActivity extends AppCompatActivity {
    private Guideline guidelineLeftLesson, getGuidelineRightLesson;
    private ImageView imageViewLesson;
    private EditText etLessonName, etLessonSubject, etSolvedProblemCountLesson;
    private Button buttonSaveLesson;
    LessonDatabaseHelper ldb;

    public void init() {
        guidelineLeftLesson = findViewById(R.id.guidelineLeftLesson);
        getGuidelineRightLesson = findViewById(R.id.guidelineRightLesson);
        imageViewLesson = findViewById(R.id.imageViewLesson);
        etLessonName = findViewById(R.id.etLessonName);
        etLessonSubject = findViewById(R.id.etLessonSubject);
        etSolvedProblemCountLesson = findViewById(R.id.etSolvedProblemCountLesson);
        buttonSaveLesson = findViewById(R.id.buttonSaveLesson);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);
        init();

        buttonSaveLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addData();

            }
        });

    }


    public void addData() {
        if (!etLessonName.getText().toString().equals("")) {
            if (!etLessonSubject.getText().toString().equals("")) {
                if (!etSolvedProblemCountLesson.getText().toString().equals("")) {
                    LessonDatabaseHelper ldb = new LessonDatabaseHelper(this);
                    String lessonName = etLessonName.getText().toString();
                    String lessonSubject = etLessonSubject.getText().toString();
                    String solvedProblemCount = etSolvedProblemCountLesson.getText().toString();
                    String dataId = dataId = getIntent().getStringExtra("dateId");;
                    ldb.AddLesson(lessonName,lessonSubject,solvedProblemCount,dataId);
                    startActivity(new Intent(AddLessonActivity.this,LessonActivity.class));

                } else
                    Toast.makeText(this, "Çözülen Problem Sayısı Boş bırakılamaz...", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Ders Konusu Boş Bırakılamaz...", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Ders Adı Boş Bırakılamaz", Toast.LENGTH_SHORT).show();
    }

}
