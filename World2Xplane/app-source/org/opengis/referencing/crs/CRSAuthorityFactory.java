package org.opengis.referencing.crs;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

@UML(identifier = "CS_CoordinateSystemAuthorityFactory", specification = Specification.OGC_01009)
public interface CRSAuthorityFactory extends AuthorityFactory {
  @UML(identifier = "createHorizontalCoordinateSystem", specification = Specification.OGC_01009)
  CoordinateReferenceSystem createCoordinateReferenceSystem(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  @UML(identifier = "createCompoundCoordinateSystem", specification = Specification.OGC_01009)
  CompoundCRS createCompoundCRS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  DerivedCRS createDerivedCRS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  EngineeringCRS createEngineeringCRS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  @UML(identifier = "createGeographicCoordinateSystem", specification = Specification.OGC_01009)
  GeographicCRS createGeographicCRS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  GeocentricCRS createGeocentricCRS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  ImageCRS createImageCRS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  @UML(identifier = "createProjectedCoordinateSystem", specification = Specification.OGC_01009)
  ProjectedCRS createProjectedCRS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  TemporalCRS createTemporalCRS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  @UML(identifier = "createVerticalCoordinateSystem", specification = Specification.OGC_01009)
  VerticalCRS createVerticalCRS(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\CRSAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */