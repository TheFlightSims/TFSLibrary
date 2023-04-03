package com.vividsolutions.jts.geom.prep;

import com.vividsolutions.jts.geom.Geometry;

public interface PreparedGeometry {
  Geometry getGeometry();
  
  boolean contains(Geometry paramGeometry);
  
  boolean containsProperly(Geometry paramGeometry);
  
  boolean coveredBy(Geometry paramGeometry);
  
  boolean covers(Geometry paramGeometry);
  
  boolean crosses(Geometry paramGeometry);
  
  boolean disjoint(Geometry paramGeometry);
  
  boolean intersects(Geometry paramGeometry);
  
  boolean overlaps(Geometry paramGeometry);
  
  boolean touches(Geometry paramGeometry);
  
  boolean within(Geometry paramGeometry);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedGeometry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */