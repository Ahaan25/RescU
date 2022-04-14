package com.example.projectrescu;

import java.io.Serializable;

public class Emergency implements Serializable {
    public String EmergencyName;
    public String[] phoneNumbers = new String[3];
    public String EmergencyMessage;

}
