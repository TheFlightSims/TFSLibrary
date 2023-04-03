package org.opengis.referencing.operation;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CC_PassThroughOperation", specification = Specification.ISO_19111)
public interface PassThroughOperation extends SingleOperation {
  @UML(identifier = "usesOperation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  Operation getOperation();
  
  @UML(identifier = "modifiedCoordinate", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  int[] getModifiedCoordinates();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\PassThroughOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */