package com.example.projectrescu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sax.EndElementListener;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Console;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class HomeActivity extends AppCompatActivity {
    private ArrayList<Emergency> emergencies = new ArrayList<>();
    private EmergencyAdapter itemsAdapter;
    private ListView lsView;
    String filename = "dataFile.srl";

    TextView outp;
    String st;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            File dataStore = new File(getFilesDir(),"" + File.separator + filename);
            dataStore.createNewFile();
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(dataStore));
            Emergency[] emer = (Emergency[]) input.readObject();
            emergencies = new ArrayList<Emergency>(Arrays.asList(emer));
            Log.d("check",emergencies.get(0).EmergencyName);
            input.close();
        }
        catch(EOFException e){

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_home);
        lsView = (ListView) findViewById(R.id.lvItems);
        if(emergencies!=null){
            itemsAdapter = new EmergencyAdapter(this,emergencies);

        }
        lsView.setAdapter(itemsAdapter);


    }

    public void openDefineEmergency(View v){
        Intent intent=new Intent(this, DefineEmergency.class);
        startActivity(intent);

    }



    public void readData() {
    }
}