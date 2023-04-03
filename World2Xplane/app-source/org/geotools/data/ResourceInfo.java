package org.geotools.data;

import java.net.URI;
import java.util.Set;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface ResourceInfo {
  String getTitle();
  
  Set<String> getKeywords();
  
  String getDescription();
  
  String getName();
  
  URI getSchema();
  
  ReferencedEnvelope getBounds();
  
  CoordinateReferenceSystem getCRS();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ResourceInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */