package edu.gatech.seclass.gobowl;

import android.icu.text.SimpleDateFormat;

import java.util.Date;

/**
 * Created by Travis on 7/7/2016.
 */
public class Score {


    private Date scoreDate;
    private int laneNumber;
    private int score;

    public Score(Date scoreDate, int score) {
        this.scoreDate = scoreDate;
        this.score = score;
    }

    public Date getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(Date scoreDate) {
        this.scoreDate = scoreDate;
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
