package com.michal.weighttrackerapp.utils;

import com.michal.weighttrackerapp.domain.BodyMeasure;

import java.util.Comparator;

public class BodyMeasureComparator implements Comparator<BodyMeasure> {
    @Override
    public int compare(BodyMeasure o1, BodyMeasure o2) {
        return o1.getDateOfMeasure().compareTo(o2.getDateOfMeasure());
    }
}
