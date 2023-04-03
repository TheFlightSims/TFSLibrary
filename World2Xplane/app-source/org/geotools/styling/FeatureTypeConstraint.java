package org.geotools.styling;

import org.opengis.filter.Filter;

public interface FeatureTypeConstraint {
  String getFeatureTypeName();
  
  void setFeatureTypeName(String paramString);
  
  Filter getFilter();
  
  void setFilter(Filter paramFilter);
  
  Extent[] getExtents();
  
  void setExtents(Extent[] paramArrayOfExtent);
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\FeatureTypeConstraint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */