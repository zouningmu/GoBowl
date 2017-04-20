package edu.gatech.seclass.gobowl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.gatech.seclass.services.CreditCardService;
import edu.gatech.seclass.services.PaymentService;
import edu.gatech.seclass.services.QRCodeService;

public class CustomerActivity extends AppCompatActivity {

    private int MAX_LANES = 20;

    private final String hashtag ="[#]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
    }

    public void backToCustomerView(View view) {
        Intent refresh = new Intent(this, CustomerActivity.class);
        startActivity(refresh);
    }

    public void openMainActivity(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openCheckoutActivity(View view) {
        setContentView(R.layout.activity_show_price);
        EditText priceText = (EditText) findViewById(R.id.priceText);
        Lane myLane = new Lane();

        priceText.setText(String.valueOf(myLane.CalculateCost()));

    }

    public void openRequestLaneActivity(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Please Scan Customer Card";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        String userId = QRCodeService.scanQRCode();
        if (userId != "ERR") {
            setContentView(R.layout.activity_request_lane);
            text = "Scan Successful";
            toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            text = "Scan Failed. Please Try Again.";
            toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void cancelLaneRequest(View view) {
        //get assigned lane
        TextView textAssignedLane = (TextView) findViewById(R.id.assignedLane);
        int assignedLane = Integer.parseInt(textAssignedLane.getText().toString());

        //unreserve lane
        LaneArray.getInstance().removeLane(assignedLane);

        //remove customers from lane
        for (Customer customer: CustomerArray.getInstance().getArrayList()) {
            if (customer.getLane() == assignedLane){
                customer.setLane(-1);
            }
        }

        //return to previous screen
        Intent refresh = new Intent(this, CustomerActivity.class);
        startActivity(refresh);
    }

    public void assignLane(View view) {
        //Get number of players
        EditText numPlayersStr = (EditText) findViewById(R.id.editText);
        int numPlayers = Integer.parseInt(numPlayersStr.getText().toString());

        //create lane that is not currently in use
        Date startPlayTime = new Date();
        Random generator = new Random();
        int laneNumber = 0;
        Lane lane;
        do{
            laneNumber = generator.nextInt(MAX_LANES) + 1;
            lane = LaneArray.getInstance().getLane(laneNumber);
        }while(lane != null);
        lane = new Lane(laneNumber, startPlayTime);
        LaneArray.getInstance().addLaneObject(lane);

        //alter screen to scan customer cards
        TextView textAssignedLaneStr = (TextView) findViewById(R.id.assignedLaneText);
        textAssignedLaneStr.setText("Assigned Lane");
        TextView textAssignedLane = (TextView) findViewById(R.id.assignedLane);
        textAssignedLane.setText(Integer.toString(laneNumber));
        numPlayersStr.setEnabled(false);
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setEnabled(false);
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setEnabled(false);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setEnabled(true);

        //Only One Customer at a time right now
        System.out.println("empty:" + CustomerArray.getInstance().getArrayList().isEmpty() );
        System.out.println("size:" + CustomerArray.getInstance().getArrayList().size() );
        if ( !CustomerArray.getInstance().getArrayList().isEmpty()) {
            CustomerArray.getInstance().getArrayList().get(0).setLane(laneNumber);
        }

        //scan player cards
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        for(int i = 0; i < numPlayers;) {
            CharSequence text = "Please Scan Player Card";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            String userId = QRCodeService.scanQRCode();
            if (userId != "ERR") {
                //determine if user is a customer
                boolean  playerIsCustomer = false;
                text = "Sorry, player is not a registered customer.";

                for (Customer customer: CustomerArray.getInstance().getArrayList()) {
                    if(customer.getUserId().equals(userId)){
                        if(customer.getLane() == -1){
                            playerIsCustomer = true;
                            customer.setLane(laneNumber);

                        } else {
                            text = "Sorry, player already has a lane checked out.";
                            numPlayers = numPlayers - 1;
                        }
                        break;
                    }
                }
                if (!playerIsCustomer){
                    toast = Toast.makeText(context, text, duration);
                    toast.show();

                    //remove invalid players from game
                    numPlayers = numPlayers - 1;
                }
                i++;
            } else {
                text = "Scan Failed. Please Try Again.";
                toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        backButton.setEnabled(true);
    }

    public void checkoutStart(View view){

        EditText numPlayersStr = (EditText) findViewById(R.id.splitCheckText);
        int numSplit = Integer.parseInt(numPlayersStr.getText().toString());
        EditText priceText = (EditText) findViewById(R.id.priceText);
        double price = Double.parseDouble(priceText.getText().toString());




        if (numSplit == 0){
            numSplit = 1;

        }

        for (Customer customer: CustomerArray.getInstance().getArrayList()) {
            System.out.println("ID: " + customer.getUserId());

            System.out.println("Lane: " + customer.getLane());
        }

        System.out.println("Number of Split: " + numSplit );
        System.out.println("Price: " + price );
        System.out.println("Price per Person: " + price/numSplit );
        String creditCardInfo;

        //TODO Make VIP add more than the first person in the list
        CustomerArray.getInstance().getArrayList().get(0).addToMoneySpent((price/numSplit));



        for (int i = 0; i < numSplit; i++) {
            Context context = getApplicationContext();
            CharSequence text = "Please Scan a credit card";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            creditCardInfo = CreditCardService.readCreditCard();
            System.out.println(creditCardInfo);

            if (creditCardInfo.equals("ERR")){
                i--;
            }else{
                String[] creditCardValues = creditCardInfo.split(hashtag);
                SimpleDateFormat expirationDateFormatter = new SimpleDateFormat("MMDDyyyy");
                try {
                    Date expirationDate = expirationDateFormatter.parse(creditCardValues[3]);
                    System.out.println(creditCardValues[0]);
                    System.out.println(creditCardValues[1]);
                    System.out.println(creditCardValues[2]);
                    System.out.println(creditCardValues[3]);
                    System.out.println(creditCardValues[4]);
                    if (!PaymentService.processPayment(creditCardValues[0],creditCardValues[1],creditCardValues[2],expirationDate,creditCardValues[4],(price/numSplit))){
                        i--;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }




            }



        }
        setContentView(R.layout.activity_add_high_score);
        Context context = getApplicationContext();
        CharSequence text = "Thank You Come Bowl Again";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        //Change when adding more customers TODO
        Lane  tempLane = new Lane();
        int x = 0;
        if (!CustomerArray.getInstance().getArrayList().isEmpty()) {
        for (Lane lane :LaneArray.getInstance().getArrayList() ) {
                if (CustomerArray.getInstance().getArrayList().get(0).getLane() == lane.getLaneNumber()) {
                    System.out.println("We Made it to the Promise land.");
                    LaneArray.getInstance().getArrayList().remove(x);

                }
                x++;
            }
            CustomerArray.getInstance().getArrayList().get(0).setLane(-1);

        }

    }

    public void backToMain(View view){
        backToCustomerView(view);
    }

    public void addScoreToList(View view){
        CustomerArray.getInstance().getArrayList().get(0).addMyScoreToHighScore();
        backToCustomerView(view);
    }

        public void showHighScores(View view){
            setContentView(R.layout.activity_show_scores);

            final EditText editScore = (EditText) findViewById(R.id.scoreText);
            final EditText editDate = (EditText) findViewById(R.id.dateText);

        Spinner spinner1 = (Spinner) findViewById(R.id.highscore);

        // Spinner Drop down elements for customer names
        List<String> scoreList = new ArrayList<String>();
        List<Score> tempScore = CustomerArray.getInstance().getArrayList().get(0).getHighScores();
        System.out.println("ScoreList: "+ tempScore.get(0).getScore());
            System.out.println("ScoreList: "+ tempScore.get(0).getScoreDate());
            System.out.println("ScoreList: "+ tempScore.get(0).getLaneNumber());
        for(Score score : tempScore){

            scoreList.add("Score: " + score.getScore() + " on " + score.getScoreDate());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, scoreList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedScore = parent.getItemAtPosition(position).toString();
                    String delims = "[ :]+";
                    String[] tokens = selectedScore.split(delims);
                    for (int x =0; x < tokens.length; x++){
                        System.out.println("token: " + tokens[x]);
                    }
                    editScore.setText(tokens[1]);
                    editDate.setText(tokens[3]+ " " + tokens[4] + " " + tokens[5] + " " +tokens[6]+":"+tokens[7]+":"+tokens[8] + " "+ tokens[9]+ " " + tokens[10]);

                }

                public void onNothingSelected(AdapterView<?> parent) {
                    System.out.println("nothing");

                }
            });
    }

}
