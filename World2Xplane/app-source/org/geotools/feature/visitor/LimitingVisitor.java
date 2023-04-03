package org.geotools.feature.visitor;

import org.opengis.feature.FeatureVisitor;

public interface LimitingVisitor extends FeatureVisitor {
  boolean hasLimits();
  
  int getStartIndex();
  
  int getMaxFeatures();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\LimitingVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */