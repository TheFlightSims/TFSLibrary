package org.opengis.referencing.operation;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CC_ConcatenatedOperation", specification = Specification.ISO_19111)
public interface ConcatenatedOperation extends CoordinateOperation {
  @UML(identifier = "usesOperation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  List<SingleOperation> getOperations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\ConcatenatedOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */