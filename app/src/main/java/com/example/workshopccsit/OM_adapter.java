package com.example.workshopccsit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OM_adapter extends RecyclerView.Adapter<ViewHolder>  {
    public int o_id;
    public String  O_name;
    private LayoutInflater layoutInflater;
    private List<MModleWorkshop> data;

    OM_adapter(ManageWorkshops context, List<MModleWorkshop> data, int O_ID, String O_name) {
        this.layoutInflater = LayoutInflater.from(context.getContext());
        this.data = data;
        this.o_id=O_ID;
        this.O_name = O_name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = layoutInflater.inflate(R.layout.custom_manage_view, viewGroup, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        MModleWorkshop WSItem      = data.get(position);
        ImageView  image          = holder.Image;
        TextView  title           = holder.textTitle;
        TextView location         = holder.Location;
        TextView textPresenter    = holder.textPresenter;



        byte[] Image = WSItem.getImage();
        final Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);



        image.setImageBitmap(bitmap);
        title.setText(WSItem.getTitle());
        location.setText(WSItem.getLocation());
        textPresenter.setText(WSItem.getPresenter());


        holder.editebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Title= data.get(position).getTitle();
                String Presenter= data.get(position).getPresenter();
                String  Date=   data.get(position).getDate();
                String location =data.get(position).getLocation();
                String Duration =data.get(position).getDuraton();
                String seatNo=data.get(position).getSeatNo();
                int id =data.get(position).getId();
                byte[] image =data.get(position).getImage();

                Intent i= new Intent(view.getContext(), Modify.class);


                i.putExtra("iTitle",Title);
                i.putExtra("iDate",Date);
                i.putExtra("iPresenter",Presenter);
                i.putExtra("iImage",image);

                i.putExtra("iLocation",location);
                i.putExtra("iSeatNo",seatNo);
                i.putExtra("iDuration",Duration);
                i.putExtra("iID",id);
                i.putExtra("OId",o_id);
                i.putExtra("O_name",O_name);
                view.getContext().startActivity(i);

            }
        });



        holder.deltebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB;
                int id =data.get(position).getId();


                  myDB = new DBHelper(view.getContext());


                Integer deleterecord =   myDB.delete(Integer.toString(id));

               if(deleterecord > 0)
                {
                    Toast.makeText(view.getContext(),"workshop has been deleted",Toast.LENGTH_LONG).show();
                    Intent i= new Intent(view.getContext(), OrganizerActivity.class);

                    i.putExtra("OId",o_id);
                    i.putExtra("O_name",O_name);

                    view.getContext().startActivity(i);
                }

                else
                {
                    Toast.makeText(view.getContext(),"Workshop can not deleted",Toast.LENGTH_LONG).show();
                }



            }
        });






    }

    @Override
    public int getItemCount() {
        return data.size();
    }




}
