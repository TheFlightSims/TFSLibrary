package org.jfree.chart.entity;

import java.util.Collection;
import java.util.Iterator;

public interface EntityCollection {
  void clear();
  
  void add(ChartEntity paramChartEntity);
  
  void addAll(EntityCollection paramEntityCollection);
  
  ChartEntity getEntity(double paramDouble1, double paramDouble2);
  
  ChartEntity getEntity(int paramInt);
  
  int getEntityCount();
  
  Collection getEntities();
  
  Iterator iterator();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\entity\EntityCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */