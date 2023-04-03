package org.opengis.referencing.operation;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.parameter.ParameterValueGroup;

@UML(identifier = "CC_Operation", specification = Specification.ISO_19111)
public interface Operation extends SingleOperation {
  @UML(identifier = "usesMethod", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  OperationMethod getMethod();
  
  @UML(identifier = "usesValue", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  ParameterValueGroup getParameterValues();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\Operation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */