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

public class OrgnizerFragment extends Fragment implements View.OnClickListener{
    Button myButton;
    EditText ID, Password;
    DBHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orgnizer, container, false);

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

        if (! (isNullOrEmpty(Password.getText().toString()) && isNullOrEmpty(ID.getText().toString()))) {

            myDB = new DBHelper(this.getActivity());

            Cursor result2 =  myDB.Organizer_login(Integer.parseInt(ID.getText().toString()),Password.getText().toString());

            if(result2.getCount() == 0)
            {
                ShowMsg("Error", "Please enter correct ID AND Password");
            }
            else{

                while(result2.moveToNext())
                {

                    id =  result2.getInt(0);
                    name =result2.getString(1) ;
                   // Toast.makeText(this.getContext(),id+name,Toast.LENGTH_LONG).show();

                }
                // get all records one by one and store it in the buffer
               Intent i = new Intent(this.getContext(),OrganizerActivity.class);

                i.putExtra("OId",id);
                i.putExtra("O_name",name);
                this.getContext().startActivity(i);

            }

        }else{

            ShowMsg("Error", "Please fill all fields");
        }

    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
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
