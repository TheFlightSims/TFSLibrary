package org.opengis.filter.capability;

import java.util.Collection;

public interface SpatialCapabilities {
  Collection<GeometryOperand> getGeometryOperands();
  
  SpatialOperators getSpatialOperators();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\capability\SpatialCapabilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */