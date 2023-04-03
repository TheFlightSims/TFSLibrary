package org.opengis.referencing.crs;

import java.util.Map;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.ObjectFactory;
import org.opengis.referencing.cs.AffineCS;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.SphericalCS;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.referencing.datum.EngineeringDatum;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.ImageDatum;
import org.opengis.referencing.datum.TemporalDatum;
import org.opengis.referencing.datum.VerticalDatum;
import org.opengis.referencing.operation.Conversion;

@UML(identifier = "CS_CoordinateSystemFactory", specification = Specification.OGC_01009)
public interface CRSFactory extends ObjectFactory {
  @UML(identifier = "createCompoundCoordinateSystem", specification = Specification.OGC_01009)
  CompoundCRS createCompoundCRS(Map<String, ?> paramMap, CoordinateReferenceSystem[] paramArrayOfCoordinateReferenceSystem) throws FactoryException;
  
  @UML(identifier = "createLocalCoordinateSystem", specification = Specification.OGC_01009)
  EngineeringCRS createEngineeringCRS(Map<String, ?> paramMap, EngineeringDatum paramEngineeringDatum, CoordinateSystem paramCoordinateSystem) throws FactoryException;
  
  ImageCRS createImageCRS(Map<String, ?> paramMap, ImageDatum paramImageDatum, AffineCS paramAffineCS) throws FactoryException;
  
  TemporalCRS createTemporalCRS(Map<String, ?> paramMap, TemporalDatum paramTemporalDatum, TimeCS paramTimeCS) throws FactoryException;
  
  @UML(identifier = "createVerticalCoordinateSystem", specification = Specification.OGC_01009)
  VerticalCRS createVerticalCRS(Map<String, ?> paramMap, VerticalDatum paramVerticalDatum, VerticalCS paramVerticalCS) throws FactoryException;
  
  GeocentricCRS createGeocentricCRS(Map<String, ?> paramMap, GeodeticDatum paramGeodeticDatum, CartesianCS paramCartesianCS) throws FactoryException;
  
  GeocentricCRS createGeocentricCRS(Map<String, ?> paramMap, GeodeticDatum paramGeodeticDatum, SphericalCS paramSphericalCS) throws FactoryException;
  
  @UML(identifier = "createGeographicCoordinateSystem", specification = Specification.OGC_01009)
  GeographicCRS createGeographicCRS(Map<String, ?> paramMap, GeodeticDatum paramGeodeticDatum, EllipsoidalCS paramEllipsoidalCS) throws FactoryException;
  
  @UML(identifier = "createFittedCoordinateSystem", specification = Specification.OGC_01009)
  DerivedCRS createDerivedCRS(Map<String, ?> paramMap, CoordinateReferenceSystem paramCoordinateReferenceSystem, Conversion paramConversion, CoordinateSystem paramCoordinateSystem) throws FactoryException;
  
  @UML(identifier = "createProjectedCoordinateSystem", specification = Specification.OGC_01009)
  ProjectedCRS createProjectedCRS(Map<String, ?> paramMap, GeographicCRS paramGeographicCRS, Conversion paramConversion, CartesianCS paramCartesianCS) throws FactoryException;
  
  @UML(identifier = "createFromXML", specification = Specification.OGC_01009)
  CoordinateReferenceSystem createFromXML(String paramString) throws FactoryException;
  
  @UML(identifier = "createFromWKT", specification = Specification.OGC_01009)
  CoordinateReferenceSystem createFromWKT(String paramString) throws FactoryException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\CRSFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */