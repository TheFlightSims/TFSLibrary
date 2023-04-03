package org.opengis.referencing.datum;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

@UML(identifier = "CS_CoordinateSystemAuthorityFactory", specification = Specification.OGC_01009)
public interface DatumAuthorityFactory extends AuthorityFactory {
  Datum createDatum(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  EngineeringDatum createEngineeringDatum(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  ImageDatum createImageDatum(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  @UML(identifier = "createVerticalDatum", specification = Specification.OGC_01009)
  VerticalDatum createVerticalDatum(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  TemporalDatum createTemporalDatum(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  @UML(identifier = "createHorizontalDatum", specification = Specification.OGC_01009)
  GeodeticDatum createGeodeticDatum(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  @UML(identifier = "createEllipsoid", specification = Specification.OGC_01009)
  Ellipsoid createEllipsoid(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  @UML(identifier = "createPrimeMeridian", specification = Specification.OGC_01009)
  PrimeMeridian createPrimeMeridian(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\datum\DatumAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */