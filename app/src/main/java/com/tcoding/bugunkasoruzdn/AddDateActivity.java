package com.tcoding.bugunkasoruzdn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddDateActivity extends AppCompatActivity {
    ImageView imageView ;
    Guideline guidelineLeft,getGuidelineRight;
    EditText etDateMonth,etDateDay;
    Button  buttonSave;

    public void init(){
        imageView = findViewById(R.id.imageView);
        guidelineLeft = findViewById(R.id.guidelineLeft);
        getGuidelineRight = findViewById(R.id.guidelineRight);
        etDateMonth = findViewById(R.id.etDateMonth);
        etDateDay = findViewById(R.id.etDateDay);
        buttonSave = findViewById(R.id.buttonSave);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);
        init();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addData();
            }
        });
    }

    void addData(){
            if(!etDateMonth.getText().toString().equals("")){
                if(!etDateDay.getText().toString().equals("")){
                    dateDatabaseHelper db = new dateDatabaseHelper(this);
                    String dateMonth = etDateMonth.getText().toString();
                    String dateDay = etDateDay.getText().toString();
                    db.AddDate(dateMonth,dateDay);
                    finish();
                    startActivity(new Intent(AddDateActivity.this,MainActivity.class));

                } else {
                    showToast("Gün Boş Bırakılamaz");
                }
            } else {
                showToast("Tarih Boş Bırakılamaz");
            }
    }

    void showToast(String mesaj){
        Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();
    }

}
