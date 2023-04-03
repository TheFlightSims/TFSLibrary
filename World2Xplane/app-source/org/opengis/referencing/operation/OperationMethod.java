package org.opengis.referencing.operation;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.util.InternationalString;

@UML(identifier = "CC_OperationMethod", specification = Specification.ISO_19111)
public interface OperationMethod extends IdentifiedObject {
  public static final String FORMULA_KEY = "formula";
  
  @UML(identifier = "formula", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  InternationalString getFormula();
  
  @UML(identifier = "sourceDimensions", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  int getSourceDimensions();
  
  @UML(identifier = "targetDimensions", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  int getTargetDimensions();
  
  @UML(identifier = "usesParameter", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  ParameterDescriptorGroup getParameters();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\OperationMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */