package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.ui.Drawable;
import org.jfree.ui.Size2D;

public interface Block extends Drawable {
  String getID();
  
  void setID(String paramString);
  
  Size2D arrange(Graphics2D paramGraphics2D);
  
  Size2D arrange(Graphics2D paramGraphics2D, RectangleConstraint paramRectangleConstraint);
  
  Rectangle2D getBounds();
  
  void setBounds(Rectangle2D paramRectangle2D);
  
  Object draw(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\Block.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */