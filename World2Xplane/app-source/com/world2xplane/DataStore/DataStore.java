package com.world2xplane.DataStore;

import java.util.List;

public interface DataStore {
  void storeNode(long paramLong, double paramDouble1, double paramDouble2);
  
  void storeRelation(long paramLong, RelationInfo paramRelationInfo);
  
  RelationInfo getRelationInfo(long paramLong);
  
  void storeWay(long paramLong, WayInfo paramWayInfo);
  
  WayInfo getWayInfo(long paramLong);
  
  List<double[]> getNodes();
  
  double[] getNode(long paramLong);
  
  int numberOfRelations();
  
  int numberOfNodes();
  
  boolean hasNode(long paramLong);
  
  boolean wayIsInRelation(long paramLong);
  
  void commit();
  
  void close();
  
  void markAsValid();
  
  boolean isValid();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\DataStore\DataStore.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */