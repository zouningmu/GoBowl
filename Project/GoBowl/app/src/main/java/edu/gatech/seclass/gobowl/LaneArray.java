package edu.gatech.seclass.gobowl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 7/6/16.
 */
public class LaneArray {
    private ArrayList<Lane> arrayList;
    private static LaneArray instance;

    private LaneArray(){
        arrayList = new ArrayList<Lane>();
    }

    public static LaneArray getInstance(){
        if (instance == null){
            instance = new LaneArray();
        }
        return instance;
    }

    public ArrayList<Lane> getArrayList() {
        return arrayList;
    }

    public void addLaneObject(Lane lane) {
        this.arrayList.add(lane);
    }

    public Lane getLane(int laneNumber) {
        for (int i = 0; i < this.arrayList.size(); i++) {
            Lane lane = this.arrayList.get(i);
            if(lane.getLaneNumber() == laneNumber){
                return lane;
            }
        }
        return null;
    }

    public void removeLane(int laneNumber) {
        for (int i = 0; i < this.arrayList.size(); i++) {
            if(this.arrayList.get(i).getLaneNumber() == laneNumber){
                this.arrayList.remove(i);
            }
        }
    }
}
