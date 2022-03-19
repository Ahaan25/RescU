package com.example.projectrescu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class DefineEmergency extends AppCompatActivity {
    Button button2;
    EditText inp;
    String st;
    Switch s;
    TextView loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_emergency);
        button2=(Button) findViewById(R.id.button2);
        inp=(EditText) findViewById(R.id.editTextTextPersonName);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DefineEmergency.this, HomeActivity.class);
                st=inp.getText().toString();
                intent.putExtra("Emergency: ", st);
                startActivity(intent);
                finish();
            }
        });
// switch button location prompt code here
        s=(Switch) findViewById(R.id.switch1);
        loc=(TextView) findViewById(R.id.textView10);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(DefineEmergency.this);
                builder.setCancelable(true);
                builder.setTitle("This emergency will send location details to emergency contacts");
                builder.setMessage("This is an alert");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loc.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();
            }
        });

//prompt code ends here
    }
}