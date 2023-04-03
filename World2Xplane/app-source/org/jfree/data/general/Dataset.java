package org.jfree.data.general;

public interface Dataset {
  void addChangeListener(DatasetChangeListener paramDatasetChangeListener);
  
  void removeChangeListener(DatasetChangeListener paramDatasetChangeListener);
  
  DatasetGroup getGroup();
  
  void setGroup(DatasetGroup paramDatasetGroup);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\Dataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */