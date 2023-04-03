package org.jfree.data.category;

public interface IntervalCategoryDataset extends CategoryDataset {
  Number getStartValue(int paramInt1, int paramInt2);
  
  Number getStartValue(Comparable paramComparable1, Comparable paramComparable2);
  
  Number getEndValue(int paramInt1, int paramInt2);
  
  Number getEndValue(Comparable paramComparable1, Comparable paramComparable2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\category\IntervalCategoryDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */