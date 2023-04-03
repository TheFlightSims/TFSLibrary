package org.geotools.filter;

import java.util.Collection;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Id;

public interface FidFilter extends Filter, Id {
  boolean contains(SimpleFeature paramSimpleFeature);
  
  void addFid(String paramString);
  
  String[] getFids();
  
  void addAllFids(Collection<?> paramCollection);
  
  void removeAllFids(Collection<?> paramCollection);
  
  void removeFid(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FidFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */