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
 * The PointClickEvent class stores data for select events on the points of the
 * chart.
 */
@DomEvent("point-select")
public class PointSelectEvent extends ComponentEvent<Chart> {

    private final int seriesItemIndex;
    private final String category;
    private final double value;
    private final int pointIndex;

    public PointSelectEvent(Chart source, boolean fromClient,
                            @EventData("event.detail.originalEvent.target.series.index") int seriesItemIndex,
                            @EventData("event.detail.originalEvent.target.category") String category,
                            @EventData("event.detail.originalEvent.target.y") double value,
                            @EventData("event.detail.originalEvent.target.index") int pointIndex) {
        super(source, fromClient);
        this.seriesItemIndex = seriesItemIndex;
        this.category = category;
        this.value = value;
        this.pointIndex = pointIndex;
    }

    public int getSeriesItemIndex() {
        return seriesItemIndex;
    }

    public String getCategory() {
        return category;
    }

    public double getValue() {
        return value;
    }

    public int getPointIndex() {
        return pointIndex;
    }
}
