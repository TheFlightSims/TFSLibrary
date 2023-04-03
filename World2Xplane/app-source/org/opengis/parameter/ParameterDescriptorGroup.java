package org.opengis.parameter;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CC_OperationParameterGroup", specification = Specification.ISO_19111)
public interface ParameterDescriptorGroup extends GeneralParameterDescriptor {
  ParameterValueGroup createValue();
  
  @UML(identifier = "includesParameter", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  List<GeneralParameterDescriptor> descriptors();
  
  GeneralParameterDescriptor descriptor(String paramString) throws ParameterNotFoundException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\ParameterDescriptorGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */