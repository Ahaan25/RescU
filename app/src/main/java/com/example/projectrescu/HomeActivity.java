package com.example.projectrescu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.sax.EndElementListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.telephony.SmsManager;
import android.app.PendingIntent;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.io.Console;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class HomeActivity extends AppCompatActivity {
    private ArrayList<Emergency> emergencies = new ArrayList<>();
    private EmergencyAdapter itemsAdapter;
    private ListView lsView;
    Map<String, String> numDict  = new HashMap<String, String>();
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

    public void AddToDict(String message,String[] numbers){
        for (String number : numbers) {
            numDict.put(number,message);
        }
    }

    public void triggerEmergency(View v){
        ViewGroup parentItem = (ViewGroup) v.getParent();
        TextView emerMessage = (TextView) parentItem.findViewById(R.id.Description);
        TextView emerPhone = (TextView) parentItem.findViewById(R.id.PhoneNumbers);
        String phoneNums = emerPhone.getText().toString();
        String message = emerMessage.getText().toString();
        String[] phoneNumbers = phoneNums.split(" ",0);
        AddToDict(message,phoneNumbers);
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.SEND_SMS)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.SEND_SMS},
                            0);
                }
        }

    }
@Override
public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
        case 0: {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                SmsManager smsManager = SmsManager.getDefault();
                for(Map.Entry<String,String> m:numDict.entrySet()){
                    smsManager.sendTextMessage(m.getKey(),null,m.getValue(),null,null);
                }
                Toast.makeText(getApplicationContext(), "SMS sent.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

}
}