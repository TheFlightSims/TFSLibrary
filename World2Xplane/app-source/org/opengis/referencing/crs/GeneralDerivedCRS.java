package org.opengis.referencing.crs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.operation.Conversion;

@UML(identifier = "SC_GeneralDerivedCRS", specification = Specification.ISO_19111)
public interface GeneralDerivedCRS extends SingleCRS {
  @UML(identifier = "baseCRS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  CoordinateReferenceSystem getBaseCRS();
  
  @UML(identifier = "definedByConversion", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  Conversion getConversionFromBase();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\GeneralDerivedCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */