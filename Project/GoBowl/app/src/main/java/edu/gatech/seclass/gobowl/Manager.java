package edu.gatech.seclass.gobowl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.services.PrintingService;

public class Manager extends AppCompatActivity {

    //ArrayList<Object> arrayCustomer = new ArrayList<Object>();
    // List<Object> arrayCustomer = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
    }

    public void openMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openAddCustomerActivity(View view) {
        setContentView(R.layout.activity_add_customer);
    }

    public void addCustomer(View view) {
        final EditText txtFirstName = (EditText) findViewById(R.id.firstNameText);
        final EditText txtLastName = (EditText) findViewById(R.id.lastNameText);
        final EditText txtEmailAddress = (EditText) findViewById(R.id.emailText);

        final String strFirstName = txtFirstName.getText().toString();
        final String strLastName = txtLastName.getText().toString();
        final String strEmailAddress = txtEmailAddress.getText().toString();


        //check for null on the input fields
        if(TextUtils.isEmpty(strFirstName)) {
            txtFirstName.setError("First Name cannot be empty.");
            return;
        }
        if(TextUtils.isEmpty(strLastName)) {
            txtLastName.setError("Last Name cannot be empty.");
            return;
        }
        if(TextUtils.isEmpty(strEmailAddress)) {
            txtEmailAddress.setError("Email Address cannot be empty.");
            return;
        }

        //check is customer already exists
        for (Customer customer : CustomerArray.getInstance().getArrayList()) {
            if (customer.getFirstName().equals(strFirstName) && customer.getLastName().equals(strLastName)) {
                Context context = getApplicationContext();
                CharSequence text = "Customer already exists ";
                //int duration = Toast.LENGTH_SHORT;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }
        }


        // create customer object
        Customer newCustomer = new Customer(
                strFirstName,
                strLastName,
                strEmailAddress);

        if (PrintingService.printCard(newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getUserId())) {
            //arrayCustomer.add(newCustomer);
            CustomerArray.getInstance().addCustomerObject(newCustomer);

            Context context = getApplicationContext();
            CharSequence text = "Customer added ";
            //int duration = Toast.LENGTH_SHORT;
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


            Intent refresh = new Intent(this, Manager.class);
            startActivity(refresh);

        } else {
            //arrayCustomer.add(newCustomer);
            CustomerArray.getInstance().addCustomerObject(newCustomer);

            Context context = getApplicationContext();
            CharSequence text = "Customer added - print card manually";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


            Intent refresh = new Intent(this, Manager.class);
            startActivity(refresh);

        }
    }

    public void openGetCustomerCardActivity(View view) {
        setContentView(R.layout.activity_print_customer);

        Spinner spinner1 = (Spinner) findViewById(R.id.customerDropDown);

        // Spinner Drop down elements for customer names
        List<String> customerList = new ArrayList<String>();
        for (Customer customer : CustomerArray.getInstance().getArrayList()) {
            customerList.add(customer.getFirstName() + " " + customer.getLastName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, customerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
    }

    public void openEditCustomerCardActivity(View view) {
        setContentView(R.layout.activity_edit_customer);

        final TextView editUserUserID = (TextView) findViewById(R.id.userIDoutput);
        final EditText editUserFirstName = (EditText) findViewById(R.id.firstName);
        final EditText editUserLastName = (EditText) findViewById(R.id.lastName);
        final EditText editUserEmailAddress = (EditText) findViewById(R.id.emailAddress);

        Spinner spinner1 = (Spinner) findViewById(R.id.customerDropDown);

        // Spinner Drop down elements for customer names
        List<String> customerList = new ArrayList<String>();
        for (Customer customer : CustomerArray.getInstance().getArrayList()) {
            customerList.add(customer.getFirstName() + " " + customer.getLastName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, customerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedUser = parent.getItemAtPosition(position).toString();

                for (Customer customer : CustomerArray.getInstance().getArrayList()) {
                    if ((customer.getFirstName() + " " + customer.getLastName()).equals(selectedUser)) {
                        editUserUserID.setText(customer.getUserId());
                        editUserFirstName.setText(customer.getFirstName());
                        editUserLastName.setText(customer.getLastName());
                        editUserEmailAddress.setText(customer.getEmailAddress());
                    }
                }

                System.out.println(selectedUser);

            }

            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("nothing");

            }
        });
    }

    public void EditCustomer(View view) {
        final TextView editUserUserID = (TextView) findViewById(R.id.userIDoutput);
        final EditText editUserFirstName = (EditText) findViewById(R.id.firstName);
        final EditText editUserLastName = (EditText) findViewById(R.id.lastName);
        final EditText editUserEmailAddress = (EditText) findViewById(R.id.emailAddress);
        Context context = getApplicationContext();

        //check for null on the input fields
        if(TextUtils.isEmpty(editUserFirstName.getText().toString())) {
            editUserFirstName.setError("First Name cannot be empty.");
            return;
        }
        if(TextUtils.isEmpty(editUserLastName.getText().toString())) {
            editUserLastName.setError("Last Name cannot be empty.");
            return;
        }
        if(TextUtils.isEmpty(editUserEmailAddress.getText().toString())) {
            editUserEmailAddress.setError("Email Address cannot be empty.");
            return;
        }

        //check is customer already exists
        for (Customer customer : CustomerArray.getInstance().getArrayList()) {
            if (customer.getFirstName().equals(editUserFirstName.getText().toString()) && customer.getLastName().equals(editUserLastName.getText().toString()) && !customer.getUserId().equals(editUserUserID.getText().toString())) {
                CharSequence text = "Customer with this name already exists ";
                //int duration = Toast.LENGTH_SHORT;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }
        }

        //update customer
        for (Customer customer : CustomerArray.getInstance().getArrayList()) {
            if (customer.getUserId().equals(editUserUserID.getText().toString())) {
                customer.setFirstName(editUserFirstName.getText().toString());
                customer.setLastName(editUserLastName.getText().toString());
                customer.setEmailAddress(editUserEmailAddress.getText().toString());
            }
        }

        CharSequence text = "Customer updated";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent refresh = new Intent(this, Manager.class);
        startActivity(refresh);
        //finish();

    }

    public void PrinCustomerCardButton(View view) {
        String firstName = "";
        String lastName = "";
        String userId = "";
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;


        Spinner spinnerCustomerDropDown = (Spinner) findViewById(R.id.customerDropDown);
        if (spinnerCustomerDropDown.getCount()==0){
            CharSequence text = "No Customers";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent refresh = new Intent(this, Manager.class);
            startActivity(refresh);
            return;
        }
        String spinerSelectedUser = spinnerCustomerDropDown.getSelectedItem().toString();


        for (Customer customer : CustomerArray.getInstance().getArrayList()) {
            if ((customer.getFirstName() + " " + customer.getLastName()).equals(spinerSelectedUser)) {
                userId = customer.getUserId();
                firstName = customer.getFirstName();
                lastName = customer.getLastName();
            }
        }


        if (PrintingService.printCard(firstName, lastName, userId)) {
            CharSequence text = "Customer Card Printed";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent refresh = new Intent(this, Manager.class);
            startActivity(refresh);
        } else {
            CharSequence text = "Printing Failed - try again";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void BackToManagerView(View view) {
        Intent refresh = new Intent(this, Manager.class);
        startActivity(refresh);
    }
}
