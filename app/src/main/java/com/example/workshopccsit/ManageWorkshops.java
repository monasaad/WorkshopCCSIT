package com.example.workshopccsit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManageWorkshops extends Fragment {
    RecyclerView recyclerView;
    OM_adapter adapter;
    ArrayList<MModleWorkshop> items;
    DBHelper myDB;
    String O_Id;
    String O_name;
    int O_ID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.manage_workshops_layout, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView_manage);

         O_Id = getArguments().getString("OId");
         O_name = getArguments().getString("O_name");
         O_ID =Integer.parseInt(O_Id);


//-------------------------      add button  //



        Button button = (Button) rootView.findViewById(R.id.deleteButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i= new Intent(v.getContext(), Addworkshop.class);
                i.putExtra("OId",O_ID);
                i.putExtra("O_name",O_name);

                v.getContext().startActivity(i);
            }
        });


//-------------------------       //


        myDB = new DBHelper(this.getActivity());

        Cursor result2 = myDB.S_View_Workshops();

        if(result2.getCount() == 0)
        {
            ShowMsg("Error", "No records found");
        }
        else{

            // get all records one by one and store it in the buffer

            items= new ArrayList<>();

            while(result2.moveToNext())
            {
                Integer  resultCountReceived = myDB.rowCountReceived(result2.getInt(0));

                items.add(new MModleWorkshop(result2.getBlob(7),result2.getInt(0),result2.getString(2) ,result2.getString(1) ,result2.getString(5),result2.getString(3),result2.getString(6),result2.getString(4),resultCountReceived));
            }


            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new OM_adapter(this,items,O_ID,O_name);
            recyclerView.setAdapter(adapter);

        }

        return rootView;
    }
    public void ShowMsg(String title, String msg){

        //builder use to set our title and message to the user
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        // to cancel it after it has been used
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);

        // show the dialog
        builder.show();


    }


}



