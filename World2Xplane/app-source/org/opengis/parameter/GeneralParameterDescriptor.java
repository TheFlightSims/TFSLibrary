package org.opengis.parameter;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.IdentifiedObject;

@UML(identifier = "CC_GeneralOperationParameter", specification = Specification.ISO_19111)
public interface GeneralParameterDescriptor extends IdentifiedObject {
  GeneralParameterValue createValue();
  
  @UML(identifier = "minimumOccurs", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  int getMinimumOccurs();
  
  @UML(identifier = "CC_OperationParameterGroup.maximumOccurs", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  int getMaximumOccurs();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\GeneralParameterDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */