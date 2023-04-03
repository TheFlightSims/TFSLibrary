package org.opengis.parameter;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CC_ParameterValueGroup", specification = Specification.ISO_19111)
public interface ParameterValueGroup extends GeneralParameterValue {
  @UML(identifier = "valuesOfGroup", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  ParameterDescriptorGroup getDescriptor();
  
  @UML(identifier = "includesValue", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  List<GeneralParameterValue> values();
  
  ParameterValue<?> parameter(String paramString) throws ParameterNotFoundException;
  
  List<ParameterValueGroup> groups(String paramString) throws ParameterNotFoundException;
  
  ParameterValueGroup addGroup(String paramString) throws ParameterNotFoundException, IllegalStateException;
  
  ParameterValueGroup clone();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\ParameterValueGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */