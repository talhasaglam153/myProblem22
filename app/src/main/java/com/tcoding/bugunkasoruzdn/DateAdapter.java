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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.myViewHolder> {
    Context myContext;
    ArrayList dateIdArray , dateMounthArray , dateDayArray ;
    dateDatabaseHelper db ;


    public DateAdapter(Context myContext, ArrayList dateIdArray, ArrayList dateMounthArray, ArrayList dateDayArray) {
        this.myContext = myContext;
        this.dateIdArray = dateIdArray;
        this.dateMounthArray = dateMounthArray;
        this.dateDayArray = dateDayArray;
    }

    @NonNull
    @Override
    public DateAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(myContext).inflate(R.layout.cardview,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapter.myViewHolder holder, final int position) {
        holder.lineer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent newIntent = new Intent(myContext,LessonActivity.class);
                    newIntent.putExtra("date",dateMounthArray.get(position).toString());
                    newIntent.putExtra("dateId",dateIdArray.get(position).toString());
                    myContext.startActivity(newIntent);

            }
        });

        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
                builder.setTitle(dateMounthArray.get(position).toString()+" Silinecek..");
                builder.setMessage(dateMounthArray.get(position).toString()+" Silmek İstedinize Emin Misiniz ?");
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Evet butonuna tıklayınca ne olacağını buraya yazacağımm
                        db = new dateDatabaseHelper(myContext);
                        db.DeleteChoose(dateIdArray.get(position).toString());
                        myContext.startActivity(new Intent(myContext,MainActivity.class));
                    }
                });
                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Hayır butonuna tıklayınca ne yapacak :D Hiç bir şey. Aynı sayfada kalmaya devam edicek
                    }
                });
                builder.create().show();
            }
        });

        holder.dateMounth.setText(String.valueOf(dateMounthArray.get(position)).toUpperCase());
        holder.dateDay.setText(String.valueOf(dateDayArray.get(position)).toUpperCase());

    }

    @Override
    public int getItemCount() {
        return dateDayArray.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lineer;
        ImageView imageViewDelete;
        CardView cardView,cardView2;
        TextView dateMounth,dateDay;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewDelete = itemView.findViewById(R.id.imageViewDeleteCardView);
            lineer = itemView.findViewById(R.id.lineer);
            cardView2 = itemView.findViewById(R.id.cardView2);
            cardView = itemView.findViewById(R.id.cardView);
            dateMounth = itemView.findViewById(R.id.dateMounthCardView);
            dateDay = itemView.findViewById(R.id.dateDayCardView);
        }
    }
}
