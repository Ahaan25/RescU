package com.example.projectrescu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class DefineEmergency extends AppCompatActivity{
    ObjectOutputStream out;
    String filename = "dataFile.srl", st;
    Button button2;
    EditText eN, eD, eP, eP2, eP3;
    Switch s;

    ArrayList<Emergency> currentEmergencies=new ArrayList<>();
    TextView loc;
    Emergency emerItem=new Emergency();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_emergency);
        try {
            File dataStore = new File(getFilesDir(),"" + File.separator + filename);
            dataStore.createNewFile();
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(dataStore));
            Emergency[] emer = (Emergency[]) input.readObject();
            currentEmergencies = new ArrayList<Emergency>(Arrays.asList(emer));
            input.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(EOFException e){

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        setContentView(R.layout.activity_define_emergency);
        button2=findViewById(R.id.button2);
        eN=findViewById(R.id.emerName);
        eD=findViewById(R.id.emerDesc);
        eP=findViewById(R.id.emerNum);
        eP2=findViewById(R.id.emerNum2);
        eP3=findViewById(R.id.emerNum3);

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(!checkphonenumbers()){
                    Toast.makeText(DefineEmergency.this, "Please fill in all fields.",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(DefineEmergency.this, HomeActivity.class);
                st=eN.getText().toString();
                emerItem.EmergencyName=st;
                st=eD.getText().toString();
                emerItem.EmergencyMessage=st;
                st=eP.getText().toString();
                emerItem.phoneNumbers[0]=st;
                st=eP2.getText().toString();
                emerItem.phoneNumbers[1]=st;
                st=eP3.getText().toString();
                emerItem.phoneNumbers[2]=st;

                currentEmergencies.add(emerItem);
                try{
                    out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
                    Emergency[] emer=new Emergency[currentEmergencies.size()];
                    currentEmergencies.toArray(emer);
                    out.writeObject(emer);
                    out.close();
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
              // intent.putExtra("Emergency: ", st);
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
    }

    public boolean checkphonenumbers(){
        if(eP.getText().toString().equals("")||eP2.getText().toString().equals("")||eP3.getText().toString().equals("")){
            return false;
        }
        else{
            return true;
        }
    }

}
