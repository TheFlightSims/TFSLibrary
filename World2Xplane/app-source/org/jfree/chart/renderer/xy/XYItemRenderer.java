package org.jfree.chart.renderer.xy;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.labels.XYSeriesLabelGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.Layer;

public interface XYItemRenderer extends LegendItemSource {
  XYItemRendererState initialise(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D, XYPlot paramXYPlot, XYDataset paramXYDataset, PlotRenderingInfo paramPlotRenderingInfo);
  
  int getPassCount();
  
  boolean getItemVisible(int paramInt1, int paramInt2);
  
  boolean isSeriesVisible(int paramInt);
  
  Boolean getSeriesVisible();
  
  void setSeriesVisible(Boolean paramBoolean);
  
  void setSeriesVisible(Boolean paramBoolean, boolean paramBoolean1);
  
  Boolean getSeriesVisible(int paramInt);
  
  void setSeriesVisible(int paramInt, Boolean paramBoolean);
  
  void setSeriesVisible(int paramInt, Boolean paramBoolean, boolean paramBoolean1);
  
  boolean getBaseSeriesVisible();
  
  void setBaseSeriesVisible(boolean paramBoolean);
  
  void setBaseSeriesVisible(boolean paramBoolean1, boolean paramBoolean2);
  
  boolean isSeriesVisibleInLegend(int paramInt);
  
  Boolean getSeriesVisibleInLegend();
  
  void setSeriesVisibleInLegend(Boolean paramBoolean);
  
  void setSeriesVisibleInLegend(Boolean paramBoolean, boolean paramBoolean1);
  
  Boolean getSeriesVisibleInLegend(int paramInt);
  
  void setSeriesVisibleInLegend(int paramInt, Boolean paramBoolean);
  
  void setSeriesVisibleInLegend(int paramInt, Boolean paramBoolean, boolean paramBoolean1);
  
  boolean getBaseSeriesVisibleInLegend();
  
  void setBaseSeriesVisibleInLegend(boolean paramBoolean);
  
  void setBaseSeriesVisibleInLegend(boolean paramBoolean1, boolean paramBoolean2);
  
  Paint getItemPaint(int paramInt1, int paramInt2);
  
  Paint getSeriesPaint(int paramInt);
  
  void setPaint(Paint paramPaint);
  
  void setSeriesPaint(int paramInt, Paint paramPaint);
  
  Paint getBasePaint();
  
  void setBasePaint(Paint paramPaint);
  
  Paint getItemOutlinePaint(int paramInt1, int paramInt2);
  
  Paint getSeriesOutlinePaint(int paramInt);
  
  void setSeriesOutlinePaint(int paramInt, Paint paramPaint);
  
  void setOutlinePaint(Paint paramPaint);
  
  Paint getBaseOutlinePaint();
  
  void setBaseOutlinePaint(Paint paramPaint);
  
  Stroke getItemStroke(int paramInt1, int paramInt2);
  
  Stroke getSeriesStroke(int paramInt);
  
  void setStroke(Stroke paramStroke);
  
  void setSeriesStroke(int paramInt, Stroke paramStroke);
  
  Stroke getBaseStroke();
  
  void setBaseStroke(Stroke paramStroke);
  
  Stroke getItemOutlineStroke(int paramInt1, int paramInt2);
  
  Stroke getSeriesOutlineStroke(int paramInt);
  
  void setOutlineStroke(Stroke paramStroke);
  
  void setSeriesOutlineStroke(int paramInt, Stroke paramStroke);
  
  Stroke getBaseOutlineStroke();
  
  void setBaseOutlineStroke(Stroke paramStroke);
  
  Shape getItemShape(int paramInt1, int paramInt2);
  
  Shape getSeriesShape(int paramInt);
  
  void setShape(Shape paramShape);
  
  void setSeriesShape(int paramInt, Shape paramShape);
  
  Shape getBaseShape();
  
  void setBaseShape(Shape paramShape);
  
  boolean isItemLabelVisible(int paramInt1, int paramInt2);
  
  boolean isSeriesItemLabelsVisible(int paramInt);
  
  void setItemLabelsVisible(boolean paramBoolean);
  
  void setItemLabelsVisible(Boolean paramBoolean);
  
  void setItemLabelsVisible(Boolean paramBoolean, boolean paramBoolean1);
  
  void setSeriesItemLabelsVisible(int paramInt, boolean paramBoolean);
  
  void setSeriesItemLabelsVisible(int paramInt, Boolean paramBoolean);
  
  void setSeriesItemLabelsVisible(int paramInt, Boolean paramBoolean, boolean paramBoolean1);
  
  Boolean getBaseItemLabelsVisible();
  
  void setBaseItemLabelsVisible(boolean paramBoolean);
  
  void setBaseItemLabelsVisible(Boolean paramBoolean);
  
  void setBaseItemLabelsVisible(Boolean paramBoolean, boolean paramBoolean1);
  
  XYItemLabelGenerator getItemLabelGenerator(int paramInt1, int paramInt2);
  
  XYItemLabelGenerator getSeriesItemLabelGenerator(int paramInt);
  
  void setItemLabelGenerator(XYItemLabelGenerator paramXYItemLabelGenerator);
  
  void setSeriesItemLabelGenerator(int paramInt, XYItemLabelGenerator paramXYItemLabelGenerator);
  
  XYItemLabelGenerator getBaseItemLabelGenerator();
  
  void setBaseItemLabelGenerator(XYItemLabelGenerator paramXYItemLabelGenerator);
  
  XYToolTipGenerator getToolTipGenerator(int paramInt1, int paramInt2);
  
  XYToolTipGenerator getSeriesToolTipGenerator(int paramInt);
  
  void setToolTipGenerator(XYToolTipGenerator paramXYToolTipGenerator);
  
  void setSeriesToolTipGenerator(int paramInt, XYToolTipGenerator paramXYToolTipGenerator);
  
  XYToolTipGenerator getBaseToolTipGenerator();
  
  void setBaseToolTipGenerator(XYToolTipGenerator paramXYToolTipGenerator);
  
  XYURLGenerator getURLGenerator();
  
  void setURLGenerator(XYURLGenerator paramXYURLGenerator);
  
  Font getItemLabelFont(int paramInt1, int paramInt2);
  
  Font getItemLabelFont();
  
  void setItemLabelFont(Font paramFont);
  
  Font getSeriesItemLabelFont(int paramInt);
  
  void setSeriesItemLabelFont(int paramInt, Font paramFont);
  
  Font getBaseItemLabelFont();
  
  void setBaseItemLabelFont(Font paramFont);
  
  Paint getItemLabelPaint(int paramInt1, int paramInt2);
  
  Paint getItemLabelPaint();
  
  void setItemLabelPaint(Paint paramPaint);
  
  Paint getSeriesItemLabelPaint(int paramInt);
  
  void setSeriesItemLabelPaint(int paramInt, Paint paramPaint);
  
  Paint getBaseItemLabelPaint();
  
  void setBaseItemLabelPaint(Paint paramPaint);
  
  ItemLabelPosition getPositiveItemLabelPosition(int paramInt1, int paramInt2);
  
  ItemLabelPosition getPositiveItemLabelPosition();
  
  void setPositiveItemLabelPosition(ItemLabelPosition paramItemLabelPosition);
  
  void setPositiveItemLabelPosition(ItemLabelPosition paramItemLabelPosition, boolean paramBoolean);
  
  ItemLabelPosition getSeriesPositiveItemLabelPosition(int paramInt);
  
  void setSeriesPositiveItemLabelPosition(int paramInt, ItemLabelPosition paramItemLabelPosition);
  
  void setSeriesPositiveItemLabelPosition(int paramInt, ItemLabelPosition paramItemLabelPosition, boolean paramBoolean);
  
  ItemLabelPosition getBasePositiveItemLabelPosition();
  
  void setBasePositiveItemLabelPosition(ItemLabelPosition paramItemLabelPosition);
  
  void setBasePositiveItemLabelPosition(ItemLabelPosition paramItemLabelPosition, boolean paramBoolean);
  
  ItemLabelPosition getNegativeItemLabelPosition(int paramInt1, int paramInt2);
  
  ItemLabelPosition getNegativeItemLabelPosition();
  
  void setNegativeItemLabelPosition(ItemLabelPosition paramItemLabelPosition);
  
  void setNegativeItemLabelPosition(ItemLabelPosition paramItemLabelPosition, boolean paramBoolean);
  
  ItemLabelPosition getSeriesNegativeItemLabelPosition(int paramInt);
  
  void setSeriesNegativeItemLabelPosition(int paramInt, ItemLabelPosition paramItemLabelPosition);
  
  void setSeriesNegativeItemLabelPosition(int paramInt, ItemLabelPosition paramItemLabelPosition, boolean paramBoolean);
  
  ItemLabelPosition getBaseNegativeItemLabelPosition();
  
  void setBaseNegativeItemLabelPosition(ItemLabelPosition paramItemLabelPosition);
  
  void setBaseNegativeItemLabelPosition(ItemLabelPosition paramItemLabelPosition, boolean paramBoolean);
  
  void addAnnotation(XYAnnotation paramXYAnnotation);
  
  void addAnnotation(XYAnnotation paramXYAnnotation, Layer paramLayer);
  
  boolean removeAnnotation(XYAnnotation paramXYAnnotation);
  
  void removeAnnotations();
  
  void drawAnnotations(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D, ValueAxis paramValueAxis1, ValueAxis paramValueAxis2, Layer paramLayer, PlotRenderingInfo paramPlotRenderingInfo);
  
  void drawItem(Graphics2D paramGraphics2D, XYItemRendererState paramXYItemRendererState, Rectangle2D paramRectangle2D, PlotRenderingInfo paramPlotRenderingInfo, XYPlot paramXYPlot, ValueAxis paramValueAxis1, ValueAxis paramValueAxis2, XYDataset paramXYDataset, int paramInt1, int paramInt2, CrosshairState paramCrosshairState, int paramInt3);
  
  LegendItem getLegendItem(int paramInt1, int paramInt2);
  
  XYSeriesLabelGenerator getLegendItemLabelGenerator();
  
  void setLegendItemLabelGenerator(XYSeriesLabelGenerator paramXYSeriesLabelGenerator);
  
  void fillDomainGridBand(Graphics2D paramGraphics2D, XYPlot paramXYPlot, ValueAxis paramValueAxis, Rectangle2D paramRectangle2D, double paramDouble1, double paramDouble2);
  
  void fillRangeGridBand(Graphics2D paramGraphics2D, XYPlot paramXYPlot, ValueAxis paramValueAxis, Rectangle2D paramRectangle2D, double paramDouble1, double paramDouble2);
  
  void drawDomainGridLine(Graphics2D paramGraphics2D, XYPlot paramXYPlot, ValueAxis paramValueAxis, Rectangle2D paramRectangle2D, double paramDouble);
  
  void drawRangeLine(Graphics2D paramGraphics2D, XYPlot paramXYPlot, ValueAxis paramValueAxis, Rectangle2D paramRectangle2D, double paramDouble, Paint paramPaint, Stroke paramStroke);
  
  void drawDomainMarker(Graphics2D paramGraphics2D, XYPlot paramXYPlot, ValueAxis paramValueAxis, Marker paramMarker, Rectangle2D paramRectangle2D);
  
  void drawRangeMarker(Graphics2D paramGraphics2D, XYPlot paramXYPlot, ValueAxis paramValueAxis, Marker paramMarker, Rectangle2D paramRectangle2D);
  
  XYPlot getPlot();
  
  void setPlot(XYPlot paramXYPlot);
  
  Range findDomainBounds(XYDataset paramXYDataset);
  
  Range findRangeBounds(XYDataset paramXYDataset);
  
  void addChangeListener(RendererChangeListener paramRendererChangeListener);
  
  void removeChangeListener(RendererChangeListener paramRendererChangeListener);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYItemRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */