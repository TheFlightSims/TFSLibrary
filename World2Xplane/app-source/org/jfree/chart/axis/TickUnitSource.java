package org.jfree.chart.axis;

public interface TickUnitSource {
  TickUnit getLargerTickUnit(TickUnit paramTickUnit);
  
  TickUnit getCeilingTickUnit(TickUnit paramTickUnit);
  
  TickUnit getCeilingTickUnit(double paramDouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\TickUnitSource.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */