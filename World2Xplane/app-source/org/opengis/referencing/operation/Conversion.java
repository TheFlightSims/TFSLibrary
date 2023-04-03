package org.opengis.referencing.operation;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

@UML(identifier = "CC_Conversion", specification = Specification.ISO_19111)
public interface Conversion extends Operation {
  @UML(identifier = "sourceCRS", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  CoordinateReferenceSystem getSourceCRS();
  
  @UML(identifier = "targetCRS", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  CoordinateReferenceSystem getTargetCRS();
  
  @UML(identifier = "operationVersion", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  String getOperationVersion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\Conversion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */