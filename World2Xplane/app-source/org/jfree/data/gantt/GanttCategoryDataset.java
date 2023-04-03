package org.jfree.data.gantt;

import org.jfree.data.category.IntervalCategoryDataset;

public interface GanttCategoryDataset extends IntervalCategoryDataset {
  Number getPercentComplete(int paramInt1, int paramInt2);
  
  Number getPercentComplete(Comparable paramComparable1, Comparable paramComparable2);
  
  int getSubIntervalCount(int paramInt1, int paramInt2);
  
  int getSubIntervalCount(Comparable paramComparable1, Comparable paramComparable2);
  
  Number getStartValue(int paramInt1, int paramInt2, int paramInt3);
  
  Number getStartValue(Comparable paramComparable1, Comparable paramComparable2, int paramInt);
  
  Number getEndValue(int paramInt1, int paramInt2, int paramInt3);
  
  Number getEndValue(Comparable paramComparable1, Comparable paramComparable2, int paramInt);
  
  Number getPercentComplete(int paramInt1, int paramInt2, int paramInt3);
  
  Number getPercentComplete(Comparable paramComparable1, Comparable paramComparable2, int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\gantt\GanttCategoryDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */