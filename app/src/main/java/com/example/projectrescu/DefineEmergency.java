package com.example.projectrescu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DefineEmergency extends AppCompatActivity {
    Button button;
    EditText inp;
    String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_emergency);
        button=(Button) findViewById(R.id.button2);
        inp=(EditText) findViewById(R.id.editTextTextPersonName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DefineEmergency.this, HomeActivity.class);
                st=inp.getText().toString();
                intent.putExtra("Emergency: ", st);
                startActivity(intent);
                finish();
            }
        });
    }
}