package com.example.workshopccsit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class S_adapter extends RecyclerView.Adapter<ViewHolder>  {
    private LayoutInflater layoutInflater;
    private List<ModelWorkshop> data;
    public int S_id;
    public String  S_name;
    S_adapter(MyWorkshops context, List<ModelWorkshop> data , int s_ID, String s_name) {
        this.layoutInflater = LayoutInflater.from(context.getContext());
        this.data = data;
        this.S_id=s_ID;
        this.S_name=s_name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = layoutInflater.inflate(R.layout.custom_my_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ModelWorkshop WSItem      = data.get(position);
        ImageView  image          = holder.Image;
        TextView  title           = holder.textTitle;
        TextView location         = holder.Location;
        TextView textPresenter    = holder.textPresenter;
        byte[] Image = WSItem.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);
        image.setImageBitmap(bitmap);
        title.setText(WSItem.getTitle());
        location.setText(WSItem.getLocation());
        textPresenter.setText(WSItem.getPresentern());
        holder.unregisterbut.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DBHelper myDB;
            int id =data.get(position).getId();
            myDB = new DBHelper(view.getContext());
            Integer deleterecord =   myDB.deleteReg(Integer.toString(id), S_id);
            if(deleterecord > 0) {
                Toast.makeText(view.getContext(),"Unregister successfully ",Toast.LENGTH_LONG).show();
                Intent i= new Intent(view.getContext(), StudentActivity.class);
                i.putExtra("S_name",S_name);
                i.putExtra("SId",S_id);
                view.getContext().startActivity(i);
            } else {
                Toast.makeText(view.getContext(),"Error can not Unregister now try later",Toast.LENGTH_LONG).show();
            }
        }
    });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
