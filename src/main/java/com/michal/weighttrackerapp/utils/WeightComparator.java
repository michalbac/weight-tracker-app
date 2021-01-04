package com.michal.weighttrackerapp.utils;

import com.michal.weighttrackerapp.domain.WeightMeasure;

import java.util.Comparator;

public class WeightComparator implements Comparator<WeightMeasure> {
    @Override
    public int compare(WeightMeasure o1, WeightMeasure o2) {
        return o1.getDateOfMeasure().compareTo(o2.getDateOfMeasure());
    }
}
