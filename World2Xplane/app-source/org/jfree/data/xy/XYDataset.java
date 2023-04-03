package org.jfree.data.xy;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.SeriesDataset;

public interface XYDataset extends SeriesDataset {
  DomainOrder getDomainOrder();
  
  int getItemCount(int paramInt);
  
  Number getX(int paramInt1, int paramInt2);
  
  double getXValue(int paramInt1, int paramInt2);
  
  Number getY(int paramInt1, int paramInt2);
  
  double getYValue(int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\XYDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */