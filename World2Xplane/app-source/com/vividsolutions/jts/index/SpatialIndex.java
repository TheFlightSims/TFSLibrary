package com.vividsolutions.jts.index;

import com.vividsolutions.jts.geom.Envelope;
import java.util.List;

public interface SpatialIndex {
  void insert(Envelope paramEnvelope, Object paramObject);
  
  List query(Envelope paramEnvelope);
  
  void query(Envelope paramEnvelope, ItemVisitor paramItemVisitor);
  
  boolean remove(Envelope paramEnvelope, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\SpatialIndex.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */