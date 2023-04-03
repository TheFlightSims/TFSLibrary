package org.opengis.filter.capability;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

public interface FilterCapabilities {
  public static final String VERSION_100 = "1.0.0";
  
  public static final String VERSION_110 = "1.1.0";
  
  public static final String VERSION_200 = "2.0.0";
  
  @UML(identifier = "scalarCapabilities", specification = Specification.UNSPECIFIED)
  ScalarCapabilities getScalarCapabilities();
  
  @UML(identifier = "spatialCapabilities", specification = Specification.UNSPECIFIED)
  SpatialCapabilities getSpatialCapabilities();
  
  @UML(identifier = "idCapabilities", specification = Specification.UNSPECIFIED)
  IdCapabilities getIdCapabilities();
  
  @UML(identifier = "temporalCapabilities", specification = Specification.UNSPECIFIED)
  TemporalCapabilities getTemporalCapabilities();
  
  String getVersion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\capability\FilterCapabilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */