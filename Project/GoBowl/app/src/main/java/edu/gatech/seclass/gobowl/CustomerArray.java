package edu.gatech.seclass.gobowl;

import java.util.ArrayList;

/**
 * Created by Usuer on 7/1/2016.
 */
public class CustomerArray {

    private ArrayList<Customer> arrayList;

    private static CustomerArray instance;

    private CustomerArray(){
        arrayList = new ArrayList<Customer>();
    }

    public static CustomerArray getInstance(){
        if (instance == null){
            instance = new CustomerArray();
        }
        return instance;
    }

    public ArrayList<Customer> getArrayList() {
        return arrayList;
    }

    public void addCustomerObject(Customer obj) {
        this.arrayList.add(obj);
    }
}
