package edu.gatech.seclass.gobowl;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Travis on 7/5/2016.
 */
public class Lane extends AppCompatActivity {
    private Date startPlaytime;
    private Date endPlaytime;
    private Date transactionTime;
    private int laneNumber;
    private boolean availableStatus;
    private double originalAmount;
    private double VIPPrice;

    public Lane(int laneNumber, Date startPlayTime){
        this.setLaneNumber(laneNumber);
        this.setStartPlaytime(startPlayTime);
    }


    public Lane() {
        super();
    }


    public Date getStartPlaytime() {
        return startPlaytime;
    }

    public void setStartPlaytime(Date startPlaytime) {
        this.startPlaytime = startPlaytime;
    }

    public Date getEndPlaytime() {
        return endPlaytime;
    }

    public void setEndPlaytime(Date endPlaytime) {
        this.endPlaytime = endPlaytime;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String originalAmounttoString() {
        return "Lane{" +
                "originalAmount=" +
                NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(originalAmount) +
                '}';
    }

    public double getVIPPrice() {
        return VIPPrice;
    }

    public void setVIPPrice(double VIPPrice) {
        this.VIPPrice = VIPPrice;
    }

    public String VIPPricetoString() {
        return "Lane{" +
                "originalAmount=" +
                NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(VIPPrice) +
                '}';
    }

    public double CalculateCost(){
        DecimalFormat twoDecimanlPlaces = new DecimalFormat(".##");
        double timePlayed = 3600000;
        this.endPlaytime = new Date( );
        Lane  tempLane = new Lane();
        if (!CustomerArray.getInstance().getArrayList().isEmpty()) {
            System.out.println("Amount of Customers: " + CustomerArray.getInstance().getArrayList().size());
            System.out.println("Customer UserId: " + CustomerArray.getInstance().getArrayList().get(0).getUserId());
            System.out.println("Customer First Name: " + CustomerArray.getInstance().getArrayList().get(0).getFirstName());
            System.out.println("Customer Last Name: " + CustomerArray.getInstance().getArrayList().get(0).getLastName());
            System.out.println("Customer Lane: " + CustomerArray.getInstance().getArrayList().get(0).getLane());


            for (Customer customer : CustomerArray.getInstance().getArrayList()) {
                for (Lane lane : LaneArray.getInstance().getArrayList()) {
                    if (customer.getLane() == lane.getLaneNumber())
                        System.out.println("We Made it to the Promise land.");
                    tempLane = lane;
                }
            }

             timePlayed = this.endPlaytime.getTime() - tempLane.startPlaytime.getTime();
        }
        //This will convert milliseconds to hours
        timePlayed =  timePlayed/3600000;

        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int dayOfWeek = currentTime.get(Calendar.DAY_OF_WEEK);
        double pricePreHour = 0;
        System.out.println("Hour of the Day: " + hour);
        System.out.println("Day of the Week: " + dayOfWeek);



        switch( dayOfWeek){
           //Sunday
            case 1:
                pricePreHour = 30;
                break;
            case 2:
                if (hour > 9 && hour < 5){
                    pricePreHour = 20;
                } else{
                    pricePreHour = 25;
                }
                break;
            case 3:
                if (hour > 9 && hour < 5){
                    pricePreHour = 20;
                } else{
                    pricePreHour = 25;
                }
                break;
            case 4:
                pricePreHour = 10;
                break;
            case 5:
                if (hour > 9 && hour < 5){
                    pricePreHour = 20;
                } else{
                    pricePreHour = 25;
                }
                break;
            case 6:
                if (hour > 9 && hour < 5){
                    pricePreHour = 20;
                } else{
                    pricePreHour = 25;
                }
                break;
            case 7:
                pricePreHour = 30;
                break;
            default:
                return 0;
        }
        //return pricePreHour;
        if (CustomerArray.getInstance().getArrayList().get(0).getMoneySpent() > 500){
            return (Double.parseDouble(twoDecimanlPlaces.format(pricePreHour * timePlayed * .9)));
        }else{
            return (Double.parseDouble(twoDecimanlPlaces.format(pricePreHour * timePlayed)));
        }

    }
}
