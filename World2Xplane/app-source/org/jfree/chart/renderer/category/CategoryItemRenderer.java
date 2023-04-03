package org.jfree.chart.renderer.category;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;

public interface CategoryItemRenderer extends LegendItemSource {
  int getPassCount();
  
  CategoryPlot getPlot();
  
  void setPlot(CategoryPlot paramCategoryPlot);
  
  void addChangeListener(RendererChangeListener paramRendererChangeListener);
  
  void removeChangeListener(RendererChangeListener paramRendererChangeListener);
  
  Range findRangeBounds(CategoryDataset paramCategoryDataset);
  
  CategoryItemRendererState initialise(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D, CategoryPlot paramCategoryPlot, int paramInt, PlotRenderingInfo paramPlotRenderingInfo);
  
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
  
  void setPaint(Paint paramPaint);
  
  Paint getSeriesPaint(int paramInt);
  
  void setSeriesPaint(int paramInt, Paint paramPaint);
  
  Paint getBasePaint();
  
  void setBasePaint(Paint paramPaint);
  
  Paint getItemOutlinePaint(int paramInt1, int paramInt2);
  
  void setOutlinePaint(Paint paramPaint);
  
  Paint getSeriesOutlinePaint(int paramInt);
  
  void setSeriesOutlinePaint(int paramInt, Paint paramPaint);
  
  Paint getBaseOutlinePaint();
  
  void setBaseOutlinePaint(Paint paramPaint);
  
  Stroke getItemStroke(int paramInt1, int paramInt2);
  
  void setStroke(Stroke paramStroke);
  
  Stroke getSeriesStroke(int paramInt);
  
  void setSeriesStroke(int paramInt, Stroke paramStroke);
  
  Stroke getBaseStroke();
  
  void setBaseStroke(Stroke paramStroke);
  
  Stroke getItemOutlineStroke(int paramInt1, int paramInt2);
  
  void setOutlineStroke(Stroke paramStroke);
  
  Stroke getSeriesOutlineStroke(int paramInt);
  
  void setSeriesOutlineStroke(int paramInt, Stroke paramStroke);
  
  Stroke getBaseOutlineStroke();
  
  void setBaseOutlineStroke(Stroke paramStroke);
  
  Shape getItemShape(int paramInt1, int paramInt2);
  
  void setShape(Shape paramShape);
  
  Shape getSeriesShape(int paramInt);
  
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
  
  CategoryItemLabelGenerator getItemLabelGenerator(int paramInt1, int paramInt2);
  
  CategoryItemLabelGenerator getSeriesItemLabelGenerator(int paramInt);
  
  void setItemLabelGenerator(CategoryItemLabelGenerator paramCategoryItemLabelGenerator);
  
  void setSeriesItemLabelGenerator(int paramInt, CategoryItemLabelGenerator paramCategoryItemLabelGenerator);
  
  CategoryItemLabelGenerator getBaseItemLabelGenerator();
  
  void setBaseItemLabelGenerator(CategoryItemLabelGenerator paramCategoryItemLabelGenerator);
  
  CategoryToolTipGenerator getToolTipGenerator(int paramInt1, int paramInt2);
  
  CategoryToolTipGenerator getToolTipGenerator();
  
  void setToolTipGenerator(CategoryToolTipGenerator paramCategoryToolTipGenerator);
  
  CategoryToolTipGenerator getSeriesToolTipGenerator(int paramInt);
  
  void setSeriesToolTipGenerator(int paramInt, CategoryToolTipGenerator paramCategoryToolTipGenerator);
  
  CategoryToolTipGenerator getBaseToolTipGenerator();
  
  void setBaseToolTipGenerator(CategoryToolTipGenerator paramCategoryToolTipGenerator);
  
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
  
  CategoryURLGenerator getItemURLGenerator(int paramInt1, int paramInt2);
  
  CategoryURLGenerator getSeriesItemURLGenerator(int paramInt);
  
  void setItemURLGenerator(CategoryURLGenerator paramCategoryURLGenerator);
  
  void setSeriesItemURLGenerator(int paramInt, CategoryURLGenerator paramCategoryURLGenerator);
  
  CategoryURLGenerator getBaseItemURLGenerator();
  
  void setBaseItemURLGenerator(CategoryURLGenerator paramCategoryURLGenerator);
  
  LegendItem getLegendItem(int paramInt1, int paramInt2);
  
  void drawBackground(Graphics2D paramGraphics2D, CategoryPlot paramCategoryPlot, Rectangle2D paramRectangle2D);
  
  void drawOutline(Graphics2D paramGraphics2D, CategoryPlot paramCategoryPlot, Rectangle2D paramRectangle2D);
  
  void drawItem(Graphics2D paramGraphics2D, CategoryItemRendererState paramCategoryItemRendererState, Rectangle2D paramRectangle2D, CategoryPlot paramCategoryPlot, CategoryAxis paramCategoryAxis, ValueAxis paramValueAxis, CategoryDataset paramCategoryDataset, int paramInt1, int paramInt2, int paramInt3);
  
  void drawDomainGridline(Graphics2D paramGraphics2D, CategoryPlot paramCategoryPlot, Rectangle2D paramRectangle2D, double paramDouble);
  
  void drawRangeGridline(Graphics2D paramGraphics2D, CategoryPlot paramCategoryPlot, ValueAxis paramValueAxis, Rectangle2D paramRectangle2D, double paramDouble);
  
  void drawDomainMarker(Graphics2D paramGraphics2D, CategoryPlot paramCategoryPlot, CategoryAxis paramCategoryAxis, CategoryMarker paramCategoryMarker, Rectangle2D paramRectangle2D);
  
  void drawRangeMarker(Graphics2D paramGraphics2D, CategoryPlot paramCategoryPlot, ValueAxis paramValueAxis, Marker paramMarker, Rectangle2D paramRectangle2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\CategoryItemRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */