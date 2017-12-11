package com.vaadin.addon.charts.events.domevents;

/*
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import com.vaadin.addon.charts.Chart;
import com.vaadin.ui.event.ComponentEvent;
import com.vaadin.ui.event.DomEvent;
import com.vaadin.ui.event.EventData;

/**
 * The PointUnselectEvent class stores data for unselect events on the points of the
 * chart.
 */
@DomEvent("point-unselect")
public class PointUnselectEvent extends ComponentEvent<Chart> implements HasItem {

    private final int seriesIndex;
    private final String category;
    private final double value;
    private final int pointIndex;

    public PointUnselectEvent(Chart source, boolean fromClient,
                              @EventData("event.detail.originalEvent.target.series.index") int seriesIndex,
                              @EventData("event.detail.originalEvent.target.category") String category,
                              @EventData("event.detail.originalEvent.target.y") double value,
                              @EventData("event.detail.originalEvent.target.index") int pointIndex) {
        super(source, fromClient);
        this.seriesIndex = seriesIndex;
        this.category = category;
        this.value = value;
        this.pointIndex = pointIndex;
    }

    @Override
    public int getSeriesItemIndex() {
        return seriesIndex;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public int getItemIndex() {
        return pointIndex;
    }
}
