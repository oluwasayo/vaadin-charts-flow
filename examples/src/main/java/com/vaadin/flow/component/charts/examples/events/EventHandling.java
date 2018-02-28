package com.vaadin.flow.component.charts.examples.events;

import com.vaadin.flow.component.charts.examples.lineandscatter.BasicLineWithCallouts;
import com.vaadin.flow.component.charts.examples.timeline.Candlestick;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.notification.Notification;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class EventHandling extends Candlestick {

    @Override
    public void initDemo() {
        super.initDemo();

        chart.getConfiguration().setTitle("Interact with the chat");
        chart.getConfiguration().setSubTitle("Mouse activity logged in stdout");

        chart.getConfiguration().setExporting(true);

        PlotOptionsCandlestick plotOptions = new PlotOptionsCandlestick();
        plotOptions.setEnableMouseTracking(true);
        plotOptions.setAllowPointSelect(true);
        plotOptions.setShowCheckbox(true);
        chart.getConfiguration().setPlotOptions(plotOptions);


        // POINT EVENTS

        chart.addPointClickListener(e -> {
            String message = String.format("Point clicked! X=%d, Y=%d, Category=%s, Value=%.2f, Series=%s, PointIndex=%d, MouseDetails=%s",
                    e.getAbsoluteX(), e.getAbsoluteY(), e.getCategory(), e.getItem().getY(),
                    e.getSeries().getName(), e.getItemIndex(), e.getMouseDetails());
            DataSeries series = (DataSeries) e.getSeries();
            toast(message);
//            series.remove(series.get(e.getItemIndex()));
            DataSeriesItem dataSeriesItem = series.get(e.getItemIndex());
            dataSeriesItem.setY(30 * Math.random());
            series.update(dataSeriesItem);
        });

        chart.addPointMouseOutListener(e -> {
            String message = String.format("Point out! Category=%s, Value=%.2f, Series=%s, PointIndex=%d",
                    e.getCategory(), e.getItem().getY(), e.getSeries().getName(), e.getItemIndex());
            System.out.println(message);
        });

        chart.addPointMouseOverListener(e -> {
            String message = String.format("Point over! Category=%s, Value=%.2f, Series=%s, PointIndex=%d",
                    e.getCategory(), e.getItem().getY(), e.getSeries().getName(), e.getItemIndex());
            System.out.println(message);
        });

        chart.addPointRemoveListener(e -> {
            String message = String.format("Point remove! Category=%s, Value=%.2f, Series=%s, PointIndex=%d",
                    e.getCategory(), e.getyValue(), e.getSeries().getName(), e.getItemIndex());
            toast(message);
        });

        chart.addPointUpdateListener(e -> {
            String message = String.format("Point update! Category=%s, OldValue=%s, NewValue=%.2f, Series=%s, PointIndex=%d",
                    e.getCategory(), e.getOldYValue(), e.getItem().getY(),
                    e.getSeries().getName(), e.getItemIndex());
            toast(message);
        });

        chart.addPointSelectListener(e -> {
            String message = String.format("Point selected! Series=%s, Category=%s, Value=%.2f, PointIndex=%d",
                    e.getSeries().getName(), e.getCategory(), e.getItem().getY(), e.getItemIndex());
            toast(message);
        });

        chart.addPointUnselectListener(e -> {
            String message = String.format("Point unselected! Series=%s, Category=%s, Value=%.2f, PointIndex=%d",
                    e.getSeries().getName(), e.getCategory(), e.getItem().getY(), e.getItemIndex());
            toast(message);
        });


        // SERIES EVENTS

        chart.addSeriesAfterAnimateListener(e -> {
            String message = String.format("Series animated! Series=%s",
                    e.getSeriesItemIndex());
            toast(message);
        });

        chart.addSeriesClickListener(e -> {
            String message = String.format("Series clicked! Series=%s, MouseDetails=%s",
                    e.getSeries().getName(), e.getMouseDetails());
            toast(message);
        });

        chart.addSeriesMouseOutListener(e -> {
            String message = String.format("Series mouse out! Series=%s",
                    e.getSeries().getName());
            System.out.println(message);
        });

        chart.addSeriesMouseOverListener(e -> {
            String message = String.format("Series mouse over! Series=%s",
                    e.getSeries().getName());
            System.out.println(message);
        });

        chart.addSeriesLegendItemClickListener(e -> {
            String message = String.format("Legend item clicked! Series=%s",
                    e.getSeries().getName());
            toast(message);
        });

        chart.addSeriesHideListener(e -> {
            String message = String.format("Series hidden! Series=%s",
                    e.getSeries().getName());
            toast(message);
        });

        chart.addSeriesShowListener(e -> {
            String message = String.format("Series shown! Series=%s",
                    e.getSeries().getName());
            toast(message);
        });

        chart.addCheckBoxClickListener(e -> {
            chart.setVisibilityTogglingDisabled(e.isChecked());
            String message = String.format("Checkbox! Series=%s, Checked=%b",
                    e.getSeries().getName(), e.isChecked());
            toast(message);
        });


        // CHART EVENTS

        chart.addChartBeforePrintListener(e -> toast("Before print!"));
        chart.addChartAfterPrintListener(e -> toast("After print!"));
        chart.addChartLoadListener(e -> toast("Load!"));
        chart.addChartRedrawListener(e -> toast("Redraw!"));

        chart.addChartAddSeriesListener(e -> {
            String message = String.format("Series added! Name=%s, Data=%s", e.getName(),
                    Arrays.asList(e.getData()).toString());
            toast(message);
        });

        chart.addChartClickListener(e -> {
            String message = String.format("Chart clicked! MouseDetails=%s",
                    e.getMouseDetails());
            toast(message);

//            Test addPoint
//            double y1 = 30 * Math.random(), y2 = 30 * Math.random();
//            int x1 = (int) (1000 * Math.random()), x2 = (int) (1000 * Math.random());
//            ((DataSeries) chart.getConfiguration().getSeries().get(0)).add(new DataSeriesItem("Month" + x1, y1), true, true);
//            ((DataSeries) chart.getConfiguration().getSeries().get(1)).add(new DataSeriesItem("Month" + x2, y2), true, true);

//            Test addSeries
            DataSeries series = new DataSeries("City " + (int) (1000 * Math.random()));
            series.setData(DoubleStream.generate(() -> 30 * Math.random()).limit(12).boxed().map(n -> (Number) n).toArray(Number[]::new));
            chart.getConfiguration().addSeries(series);
        });

        chart.getConfiguration().getChart().setZoomType(Dimension.XY);
        chart.addChartSelectionListener(e -> {
            String message = String.format("Chart selected! X=%.2f, Y=%.2f, X2=%.2f, Y2=%.2f",
                    e.getSelectionStart(), e.getValueStart(), e.getSelectionEnd(), e.getValueEnd());
            toast(message);
        });
    }

    public void toast(String message) {
        Notification notification = new Notification("", 3000, Notification.Position.TOP_END);
        notification.setText(message);
        notification.open();
    }
}
