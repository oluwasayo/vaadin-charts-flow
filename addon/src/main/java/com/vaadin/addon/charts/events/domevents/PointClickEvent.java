package com.vaadin.addon.charts.events.domevents;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.events.domevents.details.MouseEventDetails;
import com.vaadin.ui.event.ComponentEvent;
import com.vaadin.ui.event.DomEvent;
import com.vaadin.ui.event.EventData;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
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

/**
 * The PointClickEvent class stores data for click events on the points of the
 * chart.
 */
@DomEvent("point-click")
public class PointClickEvent extends ComponentEvent<Chart> implements ClickEvent, HasItem {

    private final int seriesIndex;
    private final String category;
    private final double value;
    private final int pointIndex;
    private final MouseEventDetails details;

    /**
     * Constructs a PointClickEvent
     *
     * @param source
     * @param fromClient
     * @param chartX
     * @param chartY
     * @param pageX
     * @param pageY
     * @param altKey
     * @param ctrlKey
     * @param metaKey
     * @param shiftKey
     * @param button
     * @param seriesIndex
     * @param category
     * @param value
     * @param pointIndex
     */
    public PointClickEvent(Chart source, boolean fromClient,
                           @EventData("event.detail.originalEvent.chartX") int chartX,
                           @EventData("event.detail.originalEvent.chartY") int chartY,
                           @EventData("event.detail.originalEvent.pageX") int pageX,
                           @EventData("event.detail.originalEvent.pageY") int pageY,
                           @EventData("event.detail.originalEvent.altKey") boolean altKey,
                           @EventData("event.detail.originalEvent.ctrlKey") boolean ctrlKey,
                           @EventData("event.detail.originalEvent.metaKey") boolean metaKey,
                           @EventData("event.detail.originalEvent.shiftKey") boolean shiftKey,
                           @EventData("event.detail.originalEvent.button") int button,
                           @EventData("event.detail.originalEvent.point.series.index") int seriesIndex,
                           @EventData("event.detail.originalEvent.point.category") String category,
                           @EventData("event.detail.originalEvent.point.y") double value,
                           @EventData("event.detail.originalEvent.point.index") int pointIndex) {
        super(source, fromClient);
        this.seriesIndex = seriesIndex;
        this.category = category;
        this.value = value;
        this.pointIndex = pointIndex;

        details = new MouseEventDetails();
        details.setX(chartX);
        details.setY(chartY);
        details.setAbsoluteX(pageX);
        details.setAbsoluteY(pageY);
        details.setButton(MouseEventDetails.MouseButton.of(button));
        details.setAltKey(altKey);
        details.setCtrlKey(ctrlKey);
        details.setMetaKey(metaKey);
        details.setShiftKey(shiftKey);
    }

    @Override
    public MouseEventDetails getMouseDetails() {
        return details;
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
