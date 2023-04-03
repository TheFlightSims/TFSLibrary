package org.opengis.coverage.grid;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.parameter.ParameterValueGroup;

@UML(identifier = "CV_Format", specification = Specification.OGC_01004)
public interface Format {
  @UML(identifier = "name", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  String getName();
  
  @UML(identifier = "description", obligation = Obligation.OPTIONAL, specification = Specification.OGC_01004)
  String getDescription();
  
  @UML(identifier = "vendor", obligation = Obligation.OPTIONAL, specification = Specification.OGC_01004)
  String getVendor();
  
  @UML(identifier = "docURL", obligation = Obligation.OPTIONAL, specification = Specification.OGC_01004)
  String getDocURL();
  
  @UML(identifier = "version", obligation = Obligation.OPTIONAL, specification = Specification.OGC_01004)
  String getVersion();
  
  @UML(identifier = "getParameterInfo, numParameters", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  ParameterValueGroup getReadParameters();
  
  @UML(identifier = "getParameterInfo, numParameters", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  ParameterValueGroup getWriteParameters();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\grid\Format.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */