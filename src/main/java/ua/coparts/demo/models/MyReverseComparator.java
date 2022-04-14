package ua.coparts.demo.models;

import java.util.Comparator;

public class MyReverseComparator implements Comparator<FuelPoint> {

    @Override
    public int compare(FuelPoint o1, FuelPoint o2) {
        return Integer.compare(o2.getOdometer(), o1.getOdometer());
    }

}