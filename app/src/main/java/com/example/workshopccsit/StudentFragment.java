package com.example.workshopccsit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;

public class StudentFragment extends Fragment implements View.OnClickListener {
    Button myButton;
    EditText ID, Password;
    DBHelper myDB;

    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return false;
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        myButton = view.findViewById(R.id.button);
        ID = view.findViewById(R.id.idEditText);
        Password = view.findViewById(R.id.passwordEditText);
        myButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = 0;
        String name = null;

        if (!(isNullOrEmpty(Password.getText().toString()) && isNullOrEmpty(ID.getText().toString()))) {
            myDB = new DBHelper(this.getActivity());
            Cursor result2 = myDB.student_login(Integer.parseInt(ID.getText().toString()), Password.getText().toString());

            if (result2.getCount() == 0) {
                ShowMsg("Error", "Please enter correct ID AND Password");
            } else {
                while (result2.moveToNext()) {
                    id = result2.getInt(0);
                    name = result2.getString(1);
                }
                Intent i = new Intent(this.getContext(), StudentActivity.class);
                i.putExtra("SId", id);
                i.putExtra("S_name", name);
                this.getContext().startActivity(i);
            }
        } else {
            ShowMsg("Error", "Please fill all fields");
        }
    }

    public void ShowMsg(String title, String msg) {
        //builder use to set our title and message to the user
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);

        // show the dialog
        builder.show();
    }
}
