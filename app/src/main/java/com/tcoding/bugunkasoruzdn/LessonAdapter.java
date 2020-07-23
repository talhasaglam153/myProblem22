package com.tcoding.bugunkasoruzdn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.myViewHolder> {
    Context context;
    ArrayList lessonIdArray ,lessonAdArray, lessonSubjectArray,solvedProblemCountArray,gelenDateIdArray;
    LessonDatabaseHelper ldb;

    public LessonAdapter(Context context, ArrayList lessonIdArray, ArrayList lessonAdArray, ArrayList lessonSubjectArray, ArrayList solvedProblemCountArray, ArrayList gelenDateIdArray) {
        this.context = context;
        this.lessonIdArray = lessonIdArray;
        this.lessonAdArray = lessonAdArray;
        this.lessonSubjectArray = lessonSubjectArray;
        this.solvedProblemCountArray = solvedProblemCountArray;
        this.gelenDateIdArray = gelenDateIdArray;
    }

    @NonNull
    @Override
    public LessonAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.lesson_cardview,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.myViewHolder holder, final int position) {
        holder.lineerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.lessonNameTextCardView.setText(lessonAdArray.get(position).toString().toUpperCase());
        holder.lessonSubjectTextCardView.setText(lessonSubjectArray.get(position).toString().toUpperCase());
        holder.solvedProblemCountTextCardView.setText(solvedProblemCountArray.get(position).toString().toUpperCase());
        holder.imageViewDeleteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ldb = new LessonDatabaseHelper(context);
                ldb.DeleteChoosing(lessonIdArray.get(position).toString());
                context.startActivity(new Intent(context,LessonActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return lessonAdArray.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lineerCardView;
        CardView cardViewLesson;
        TextView lessonNameTextCardView , lessonSubjectTextCardView,solvedProblemCountTextCardView;
        ImageView imageViewDeleteCardView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            lineerCardView = itemView.findViewById(R.id.lineerCardView);
            cardViewLesson = itemView.findViewById(R.id.cardViewLesson);
            lessonNameTextCardView = itemView.findViewById(R.id.lessonNameTextCardView);
            lessonSubjectTextCardView = itemView.findViewById(R.id.lessonSubjectTextCardView);
            solvedProblemCountTextCardView = itemView.findViewById(R.id.solvedProblemCountTextCardView);
            imageViewDeleteCardView = itemView.findViewById(R.id.imageViewDeleteCardView);

        }
    }
}
