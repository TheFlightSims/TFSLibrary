package org.opengis.parameter;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.Cloneable;

@UML(identifier = "CC_GeneralParameterValue", specification = Specification.ISO_19111)
public interface GeneralParameterValue extends Cloneable {
  GeneralParameterDescriptor getDescriptor();
  
  GeneralParameterValue clone();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\GeneralParameterValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */