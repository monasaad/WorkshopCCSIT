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

public class ViewWorkshops extends Fragment {
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<ModelWorkshop> items;
    DBHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.viwe_workshop_layout, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView_View_workshop);
        String Stu_Id = getArguments().getString("stu_ID");
        String Stu_name = getArguments().getString("S_name");
        int S_ID = Integer.parseInt(Stu_Id);
        myDB = new DBHelper(this.getActivity());
        Cursor result2 = myDB.S_View_Workshops();

        if (result2.getCount() == 0) {
            ShowMsg("Sorry!", "There is no available workshop.");
        } else {

            // get all records one by one and store it in the buffer

            items = new ArrayList<>();

            while (result2.moveToNext()) {
                Integer resultCountReceived = myDB.rowCountReceived(result2.getInt(0));
                items.add(new ModelWorkshop(result2.getBlob(7), result2.getInt(0), result2.getString(2), result2.getString(1), result2.getString(5), result2.getString(3), result2.getString(6), result2.getString(4), resultCountReceived));
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new Adapter(this, items, S_ID, Stu_name);
            recyclerView.setAdapter(adapter);

        }

        return rootView;
    }

    public void ShowMsg(String title, String msg) {

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



