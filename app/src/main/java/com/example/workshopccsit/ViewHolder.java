package com.example.workshopccsit;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import lecho.lib.hellocharts.view.PieChartView;

public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
    TextView textTitle,Location,textPresenter, username;
    PieChartView pieChartView;
    ImageView Image;
    Button editebut,deltebut ,unregisterbut;
    ImageButton attend;
    CheckBox checkateend;
    ItemOnClickLestener itemOnClickLestener;



   ViewHolder(@NonNull View itemView ){
        super(itemView);
        pieChartView = itemView.findViewById(R.id.chartpie);
        textTitle =itemView.findViewById(R.id.textTitle);
        Location =itemView.findViewById(R.id.Location);
        Image =itemView.findViewById(R.id.image);
        textPresenter =itemView.findViewById(R.id.textPresenter);

        editebut=itemView.findViewById(R.id.Editbutton);
        deltebut=itemView.findViewById(R.id.deleteButton);
        unregisterbut=itemView.findViewById(R.id.button2);

        attend=itemView.findViewById(R.id.icon_attend);
        username = itemView.findViewById(R.id.Username);
        checkateend =itemView.findViewById(R.id.checkAttend);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
     this.itemOnClickLestener.onItemOnClickLestener(view,getAdapterPosition());
    }

    public void setItemOnClickLestener(ItemOnClickLestener ic){
       this.itemOnClickLestener =ic;
    }
}