package org.jfree.chart.plot;

import java.awt.geom.Point2D;

public interface Zoomable {
  boolean isDomainZoomable();
  
  boolean isRangeZoomable();
  
  PlotOrientation getOrientation();
  
  void zoomDomainAxes(double paramDouble, PlotRenderingInfo paramPlotRenderingInfo, Point2D paramPoint2D);
  
  void zoomDomainAxes(double paramDouble1, double paramDouble2, PlotRenderingInfo paramPlotRenderingInfo, Point2D paramPoint2D);
  
  void zoomRangeAxes(double paramDouble, PlotRenderingInfo paramPlotRenderingInfo, Point2D paramPoint2D);
  
  void zoomRangeAxes(double paramDouble1, double paramDouble2, PlotRenderingInfo paramPlotRenderingInfo, Point2D paramPoint2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\Zoomable.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */