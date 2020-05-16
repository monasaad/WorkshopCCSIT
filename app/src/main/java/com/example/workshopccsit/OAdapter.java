package com.example.workshopccsit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class OAdapter extends RecyclerView.Adapter<ViewHolder> {
private LayoutInflater layoutInflater;
private List<MModleWorkshop> data;
    public int o_id;
    public String  O_name;

    OAdapter(OViewWorkshops context , List<MModleWorkshop> data , int O_ID, String O_name) {
        this.layoutInflater = LayoutInflater.from(context.getContext());
        this.data = data;
        this.o_id=O_ID;
        this.O_name = O_name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = layoutInflater.inflate(R.layout.o_custom_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MModleWorkshop WSItem     = data.get(position);
        ImageView  image          = holder.Image;
        TextView  title           = holder.textTitle;
        TextView Location         = holder.Location;
        TextView textPresenter    = holder.textPresenter;
        int seets                 = Integer.parseInt(data.get(position).getSeatNo());
        PieChartView pieChartView = holder.pieChartView;
///


        List pieData = new ArrayList<>();

        pieData.add(new SliceValue(data.get(position).getReg_count(), Color.parseColor("#9A7D46")));
        pieData.add(new SliceValue(seets-data.get(position).getReg_count(), Color.parseColor("lightgray")));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(false).setValueLabelTextSize(16);
        pieChartData.setHasCenterCircle(true).setCenterText1(seets-data.get(position).getReg_count()+"/"+seets).setCenterText1FontSize(14).setCenterText1Color(Color.parseColor("#0D4040"));

        pieChartView.setPieChartData(pieChartData);


        byte[] Image = WSItem.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);


        image.setImageBitmap(bitmap);
        title.setText(WSItem.getTitle());
        Location.setText(WSItem.getLocation());
        textPresenter.setText(WSItem.getPresenter());

///


        holder.attend.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                int id = data.get(position).getId();
                String Title= data.get(position).getTitle();
                Intent i = new Intent(view.getContext(), Attendance.class);
                i.putExtra("iTitle",Title);
                i.putExtra("WId",id);

                view.getContext().startActivity(i);

            }});




////

        holder.setItemOnClickLestener(new ItemOnClickLestener() {
                                          @Override
                                          public void onItemOnClickLestener(View v, int position) {


                                              String Title= data.get(position).getTitle();
                                              String Presenter= data.get(position).getPresenter();
                                              String  Date=   data.get(position).getDate();
                                              String location =data.get(position).getLocation();
                                              String Duration =data.get(position).getDuraton();
                                              String seatNo=data.get(position).getSeatNo();
                                              int id =data.get(position).getId();
                                              byte[] image =data.get(position).getImage();

                                              Intent i;

                                              i = new Intent(layoutInflater.getContext(),Oview_Wstatus.class);

                                              i.putExtra("iID",id);

                                              i.putExtra("iTitle",Title);
                                              i.putExtra("iDate",Date);
                                              i.putExtra("iPresenter",Presenter);
                                              i.putExtra("iImage",image);

                                              i.putExtra("iLocation",location);
                                              i.putExtra("iSeatNo",seatNo);
                                              i.putExtra("iDuration",Duration);


                                              i.putExtra("OId",o_id);
                                              i.putExtra("O_name",O_name);

                                              layoutInflater.getContext().startActivity(i);
                                          }
                                      }


        );
}


    @Override
    public int getItemCount() {
        return data.size();
    }



}



