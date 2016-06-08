package com.firstclasstax;

/**
 * Created by Brandon on 5/27/2016.
 */
public class TripItem {
    public final String location;
    public final String dates;
    public final String perDiem;

    public TripItem(String location, String dates, String perDiem){
        this.location = location;
        this.dates = dates;
        this.perDiem = perDiem;
    }
}
