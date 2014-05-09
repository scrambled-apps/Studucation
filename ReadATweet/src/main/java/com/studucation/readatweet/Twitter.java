package com.studucation.readatweet;

import java.util.ArrayList;

import com.studucation.readatweet.Tweet;


// a collection of tweets
public class Twitter extends ArrayList<Tweet> {
    private static final long serialVersionUID = 1L;


    public String toString() {
        String ret = "[Eerste 5 berichten: ";
        try{
        for (int i = 0; i < 5; i++) {
            ret = ret + " {" + this.get(i).getText() + "}";
        }
        }catch (ArrayIndexOutOfBoundsException a){
            a.printStackTrace();
            ret = ret + "}";
        }
        ret = ret + "]";
        return ret;

    }
}