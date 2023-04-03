package org.opengis.referencing.operation;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

@UML(identifier = "CC_Transformation", specification = Specification.ISO_19111)
public interface Transformation extends Operation {
  @UML(identifier = "sourceCRS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  CoordinateReferenceSystem getSourceCRS();
  
  @UML(identifier = "targetCRS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  CoordinateReferenceSystem getTargetCRS();
  
  @UML(identifier = "operationVersion", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  String getOperationVersion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\Transformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */