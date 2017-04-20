package edu.gatech.seclass.gobowl;


import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Sylvester on 7/1/2016.
 */
public class Customer extends AppCompatActivity {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userId;
    private String tempId;
    private String qrCode;
    private int assignedLane;



    private double moneySpent;

    private Score myScore;



    private List<Score>   highScores = new ArrayList<Score>();



    public Customer(String fName, String lName, String eAddress){
        this.firstName = fName;
        this.lastName = lName;
        this.emailAddress = eAddress;
        this.assignedLane = -1;
        this.moneySpent = 0;

        //String tempUserId = String.format("%04X",(int)(Math.random()*10000));
        this.userId = createUniqueUserId();
        System.out.println("hex " +this.userId);
    }

    private String createUniqueUserId() {
        while (checkUniqueId()) {
            return tempId;
        }
        return tempId;
    }

    private Boolean checkUniqueId(){
        String tempUserId = String.format("%04X",(int)(Math.random()*10000));

        for (Customer customer: CustomerArray.getInstance().getArrayList()) {
            if (customer.getUserId() == tempUserId){
                return false;
            }
        }
        tempId = tempUserId;
        return true;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getLane() { return assignedLane; }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public void setLane(int laneNumber) { this.assignedLane = laneNumber; }

    public List<Score> getHighScores() {
        return highScores;
    }


    public void addMyScoreToHighScore() {
        Random ran = new Random();
        int x = ran.nextInt(300);
        Date todayDate = new Date();
        this.myScore = new Score(todayDate, x);
        highScores.add(myScore);
        System.out.println("Today's socre is added to your list. " + myScore.getScore() );
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void addToMoneySpent(double money){
        this.moneySpent = this.moneySpent + money;
    }

}
