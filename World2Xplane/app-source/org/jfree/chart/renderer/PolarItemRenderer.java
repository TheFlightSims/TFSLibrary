package org.jfree.chart.renderer;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.data.xy.XYDataset;

public interface PolarItemRenderer {
  void drawSeries(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D, PlotRenderingInfo paramPlotRenderingInfo, PolarPlot paramPolarPlot, XYDataset paramXYDataset, int paramInt);
  
  void drawAngularGridLines(Graphics2D paramGraphics2D, PolarPlot paramPolarPlot, List paramList, Rectangle2D paramRectangle2D);
  
  void drawRadialGridLines(Graphics2D paramGraphics2D, PolarPlot paramPolarPlot, ValueAxis paramValueAxis, List paramList, Rectangle2D paramRectangle2D);
  
  LegendItem getLegendItem(int paramInt);
  
  PolarPlot getPlot();
  
  void setPlot(PolarPlot paramPolarPlot);
  
  void addChangeListener(RendererChangeListener paramRendererChangeListener);
  
  void removeChangeListener(RendererChangeListener paramRendererChangeListener);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\PolarItemRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */