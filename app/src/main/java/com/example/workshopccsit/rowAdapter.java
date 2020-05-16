package com.example.workshopccsit;

import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class rowAdapter extends RecyclerView.Adapter<ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Country> data;
    int Wid;

    rowAdapter(Attendance context , List<Country> data , int wid) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.Wid=wid;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_attend,viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Country WSItem = data.get(position);
        TextView name = holder.username;
        CheckBox checkAttend = holder.checkateend;
        name.setText(WSItem.getName());
        int attend = data.get(position).getATTEND();
        if (attend==0){
            checkAttend.setChecked(true);
        } else{
            checkAttend.setChecked(false);
        }

        holder.checkateend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(((CheckBox)v).isChecked()) {

                    DBHelper myDB;
                    myDB = new DBHelper(v.getContext());
                    Cursor curs= myDB.Student_ID(data.get(position).getName());
                    curs.moveToNext();
                    int id= curs.getInt(0);
                    Boolean result = myDB.check(0,id,Wid);


                }
                else {

                    DBHelper myDB;
                    myDB = new DBHelper(v.getContext());
                    Cursor curs= myDB.Student_ID(data.get(position).getName());
                    curs.moveToNext();
                    int id= curs.getInt(0);
                    Boolean result = myDB.check(1,id,Wid);
                }
            }
        });
 }

    @Override
    public int getItemCount() {
        return data.size();
    }
}