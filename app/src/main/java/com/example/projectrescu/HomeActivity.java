package com.example.projectrescu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {
    TextView outp;
    String st;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        outp=findViewById(R.id.textView7);
        st=getIntent().getExtras().getString("Emergency: ");
        outp.setText(st);

        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDefineEmergency();
            }
        });
    }

    public void openDefineEmergency(){
        Intent intent=new Intent(this, DefineEmergency.class);
        startActivity(intent);

    }
}