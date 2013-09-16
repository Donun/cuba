/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */
package com.haulmont.cuba.gui.aggregation;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.ArrayUtils;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class NumberAggregationHelper {
    private final List<Double> items;

    public NumberAggregationHelper() {
        items = new ArrayList<Double>();
    }

    public void addItem(Double newItem) {
        items.add(newItem);
    }

    public Double sum() {
        double sum = 0d;
        for (final Double item : items) {
            if (item != null) {
                sum += item;
            }
        }
        return sum;
    }

    public Double avg() {
        return sum () / items.size();
    }

    public Double min() {
        return NumberUtils.min(ArrayUtils.toPrimitive(items.toArray(new Double[items.size()])));
    }

    public Double max() {
        return NumberUtils.max(ArrayUtils.toPrimitive(items.toArray(new Double[items.size()])));
    }
}
