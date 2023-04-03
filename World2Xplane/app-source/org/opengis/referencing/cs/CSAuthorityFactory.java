package org.opengis.referencing.cs;

import javax.measure.unit.Unit;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

public interface CSAuthorityFactory extends AuthorityFactory {
  CoordinateSystem createCoordinateSystem(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  CartesianCS createCartesianCS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  PolarCS createPolarCS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  CylindricalCS createCylindricalCS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  SphericalCS createSphericalCS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  EllipsoidalCS createEllipsoidalCS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  VerticalCS createVerticalCS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  TimeCS createTimeCS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  CoordinateSystemAxis createCoordinateSystemAxis(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  @UML(identifier = "CS_CoordinateSystemAuthorityFactory.createLinearUnit, createAngularUnit", specification = Specification.OGC_01009)
  Unit<?> createUnit(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\cs\CSAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */