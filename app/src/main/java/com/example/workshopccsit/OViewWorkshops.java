package com.example.workshopccsit;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class OViewWorkshops extends Fragment {
    RecyclerView recyclerView;
    OAdapter adapter;
    ArrayList<MModleWorkshop> items;
    DBHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.o_viwe_workshop_layout, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView_O_view);

        String O_Id = getArguments().getString("OId");
        String O_name = getArguments().getString("O_name");
        int O_ID =Integer.parseInt(O_Id);

        myDB = new DBHelper(this.getActivity());

        Cursor result2 = myDB.S_View_Workshops();

        if(result2.getCount() == 0) {
            ShowMsg("Error", "No records found");
        }
        else {

            // get all records one by one and store it in the buffer

            items= new ArrayList<>();

            while(result2.moveToNext())
            {
                Integer  resultCountReceived = myDB.rowCountReceived(result2.getInt(0));

                items.add(new MModleWorkshop(result2.getBlob(7),result2.getInt(0),result2.getString(2) ,result2.getString(1) ,result2.getString(5),result2.getString(3),result2.getString(6),result2.getString(4),resultCountReceived));
            }



            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new OAdapter(this,items,O_ID,O_name);
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