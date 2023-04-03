package org.opengis.referencing.datum;

import java.util.Date;
import java.util.Map;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.unit.Unit;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.ObjectFactory;

@UML(identifier = "CS_CoordinateSystemFactory", specification = Specification.OGC_01009)
public interface DatumFactory extends ObjectFactory {
  @UML(identifier = "createLocalDatum", specification = Specification.OGC_01009)
  EngineeringDatum createEngineeringDatum(Map<String, ?> paramMap) throws FactoryException;
  
  @UML(identifier = "createHorizontalDatum", specification = Specification.OGC_01009)
  GeodeticDatum createGeodeticDatum(Map<String, ?> paramMap, Ellipsoid paramEllipsoid, PrimeMeridian paramPrimeMeridian) throws FactoryException;
  
  ImageDatum createImageDatum(Map<String, ?> paramMap, PixelInCell paramPixelInCell) throws FactoryException;
  
  TemporalDatum createTemporalDatum(Map<String, ?> paramMap, Date paramDate) throws FactoryException;
  
  @UML(identifier = "createVerticalDatum", specification = Specification.OGC_01009)
  VerticalDatum createVerticalDatum(Map<String, ?> paramMap, VerticalDatumType paramVerticalDatumType) throws FactoryException;
  
  @UML(identifier = "createEllipsoid", specification = Specification.OGC_01009)
  Ellipsoid createEllipsoid(Map<String, ?> paramMap, double paramDouble1, double paramDouble2, Unit<Length> paramUnit) throws FactoryException;
  
  @UML(identifier = "createFlattenedSphere", specification = Specification.OGC_01009)
  Ellipsoid createFlattenedSphere(Map<String, ?> paramMap, double paramDouble1, double paramDouble2, Unit<Length> paramUnit) throws FactoryException;
  
  @UML(identifier = "createPrimeMeridian", specification = Specification.OGC_01009)
  PrimeMeridian createPrimeMeridian(Map<String, ?> paramMap, double paramDouble, Unit<Angle> paramUnit) throws FactoryException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\datum\DatumFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */