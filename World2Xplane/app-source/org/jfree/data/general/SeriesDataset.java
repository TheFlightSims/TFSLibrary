package org.jfree.data.general;

public interface SeriesDataset extends Dataset {
  int getSeriesCount();
  
  Comparable getSeriesKey(int paramInt);
  
  int indexOf(Comparable paramComparable);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\SeriesDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */