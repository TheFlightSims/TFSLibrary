package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.PropertyIsLike;

public interface LikeFilter extends Filter, PropertyIsLike {
  void setPattern(String paramString1, String paramString2, String paramString3, String paramString4);
  
  String getWildcardMulti();
  
  String getEscape();
  
  void setPattern(Expression paramExpression, String paramString1, String paramString2, String paramString3);
  
  String getPattern();
  
  void setValue(Expression paramExpression) throws IllegalFilterException;
  
  Expression getValue();
  
  String getWildcardSingle();
  
  boolean contains(SimpleFeature paramSimpleFeature);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\LikeFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */