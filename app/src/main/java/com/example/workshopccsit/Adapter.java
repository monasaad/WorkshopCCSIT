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
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    public int S_id;
    public String S_name;
    private LayoutInflater layoutInflater;
    private List<ModelWorkshop> data;

    Adapter(ViewWorkshops context, List<ModelWorkshop> data, int s_ID, String s_name) {
        this.layoutInflater = LayoutInflater.from(context.getContext());
        this.data = data;
        this.S_id = s_ID;
        this.S_name = s_name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ModelWorkshop WSItem = data.get(position);
        ImageView image = holder.Image;
        TextView title = holder.textTitle;
        TextView location = holder.Location;
        TextView textPresenter = holder.textPresenter;
        PieChartView pieChartView = holder.pieChartView;
        int seets = Integer.parseInt(data.get(position).getSeatNo());
///

        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(data.get(position).getReg_count(), Color.parseColor("#9A7D46")));
        pieData.add(new SliceValue(seets - data.get(position).getReg_count(), Color.parseColor("lightgray")));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(false).setValueLabelTextSize(16);
        pieChartData.setHasCenterCircle(true).setCenterText1(seets - data.get(position).getReg_count() + "/" + seets).setCenterText1FontSize(14).setCenterText1Color(Color.parseColor("#0D4040"));
        pieChartView.setPieChartData(pieChartData);
////
        byte[] Image = WSItem.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);
        image.setImageBitmap(bitmap);
///
        title.setText(WSItem.getTitle());
        location.setText(WSItem.getLocation());
        textPresenter.setText(WSItem.getPresentern());


        holder.setItemOnClickLestener(new ItemOnClickLestener() {
            @Override
            public void onItemOnClickLestener(View v, int position) {
              String Title = data.get(position).getTitle();
              String Presenter = data.get(position).getPresentern();
              String Date = data.get(position).getData();
              String location = data.get(position).getLocation();
              String Duration = data.get(position).getDuraton();
              String seatNo = data.get(position).getSeatNo();
              int reg_count = data.get(position).getReg_count();
              byte[] image = data.get(position).getImage();
              int id = data.get(position).getId();

              Intent i;
              i = new Intent(layoutInflater.getContext(), RegasterIN.class);
              i.putExtra("iTitle", Title);
              i.putExtra("iDate", Date);
              i.putExtra("iPresenter", Presenter);
              i.putExtra("iID", id);
              i.putExtra("S_ID", S_id);
              i.putExtra("iImage", image);
              i.putExtra("iLocation", location);
              i.putExtra("iSeatNo", seatNo);
              i.putExtra("iDuration", Duration);
              i.putExtra("REG_COUNT", reg_count);
              i.putExtra("S_name", S_name);
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
