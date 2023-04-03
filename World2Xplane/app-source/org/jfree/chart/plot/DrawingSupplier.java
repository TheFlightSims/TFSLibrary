package org.jfree.chart.plot;

import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

public interface DrawingSupplier {
  Paint getNextPaint();
  
  Paint getNextOutlinePaint();
  
  Stroke getNextStroke();
  
  Stroke getNextOutlineStroke();
  
  Shape getNextShape();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\DrawingSupplier.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */