package org.opengis.referencing.cs;

import java.util.Map;
import javax.measure.unit.Unit;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.ObjectFactory;

public interface CSFactory extends ObjectFactory {
  CoordinateSystemAxis createCoordinateSystemAxis(Map<String, ?> paramMap, String paramString, AxisDirection paramAxisDirection, Unit<?> paramUnit) throws FactoryException;
  
  CartesianCS createCartesianCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2) throws FactoryException;
  
  CartesianCS createCartesianCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2, CoordinateSystemAxis paramCoordinateSystemAxis3) throws FactoryException;
  
  AffineCS createAffineCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2) throws FactoryException;
  
  AffineCS createAffineCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2, CoordinateSystemAxis paramCoordinateSystemAxis3) throws FactoryException;
  
  PolarCS createPolarCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2) throws FactoryException;
  
  CylindricalCS createCylindricalCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2, CoordinateSystemAxis paramCoordinateSystemAxis3) throws FactoryException;
  
  SphericalCS createSphericalCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2, CoordinateSystemAxis paramCoordinateSystemAxis3) throws FactoryException;
  
  EllipsoidalCS createEllipsoidalCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2) throws FactoryException;
  
  EllipsoidalCS createEllipsoidalCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2, CoordinateSystemAxis paramCoordinateSystemAxis3) throws FactoryException;
  
  VerticalCS createVerticalCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis) throws FactoryException;
  
  TimeCS createTimeCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis) throws FactoryException;
  
  LinearCS createLinearCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis) throws FactoryException;
  
  UserDefinedCS createUserDefinedCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2) throws FactoryException;
  
  UserDefinedCS createUserDefinedCS(Map<String, ?> paramMap, CoordinateSystemAxis paramCoordinateSystemAxis1, CoordinateSystemAxis paramCoordinateSystemAxis2, CoordinateSystemAxis paramCoordinateSystemAxis3) throws FactoryException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\cs\CSFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */