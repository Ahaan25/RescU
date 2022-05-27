package com.example.projectrescu;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EmergencyAdapter extends ArrayAdapter<Emergency> {

    TextView eName, eDescription, eNumbers;

    private final Context context;
    private final ArrayList<Emergency> emergencies;

    public EmergencyAdapter(Context context, ArrayList<Emergency> eList){
        super(context,0,eList);
        this.context = context;
        this.emergencies = eList;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View rowView=convertView;
        if(rowView==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             rowView=inflater.inflate(R.layout.list_item,parent,false);
        }

        eName=rowView.findViewById(R.id.Name);
        eDescription=rowView.findViewById(R.id.Description);
        eNumbers=rowView.findViewById(R.id.PhoneNumbers);
        eName.setText(emergencies.get(position).EmergencyName);
        eDescription.setText(emergencies.get(position).EmergencyMessage);
        String phoneString="";
        for (String item:emergencies.get(position).phoneNumbers){
            phoneString+=(item+"");
        }
        eNumbers.setText(phoneString);
        return rowView;
    }

}
