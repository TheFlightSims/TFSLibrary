package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;

@UML(identifier = "GM_GenericCurve", specification = Specification.ISO_19107)
public interface GenericCurve {
  @UML(identifier = "startPoint", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition getStartPoint();
  
  @UML(identifier = "endPoint", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition getEndPoint();
  
  @UML(identifier = "tangent", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double[] getTangent(double paramDouble);
  
  @UML(identifier = "startParam", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getStartParam();
  
  @UML(identifier = "endParam", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getEndParam();
  
  @UML(identifier = "startConstrParam", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getStartConstructiveParam();
  
  @UML(identifier = "endConstrParam", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getEndConstructiveParam();
  
  @UML(identifier = "constrParam", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition forConstructiveParam(double paramDouble);
  
  @UML(identifier = "param", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition forParam(double paramDouble);
  
  @UML(identifier = "paramForPoint", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  ParamForPoint getParamForPoint(DirectPosition paramDirectPosition);
  
  @UML(identifier = "length", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double length(Position paramPosition1, Position paramPosition2);
  
  @UML(identifier = "length", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double length(double paramDouble1, double paramDouble2);
  
  @UML(identifier = "asLineString", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  LineString asLineString(double paramDouble1, double paramDouble2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\GenericCurve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */