package org.opengis.parameter;

import java.util.Set;
import javax.measure.unit.Unit;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CC_OperationParameter", specification = Specification.ISO_19111)
public interface ParameterDescriptor<T> extends GeneralParameterDescriptor {
  ParameterValue<T> createValue();
  
  @UML(identifier = "GC_ParameterInfo.type", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  Class<T> getValueClass();
  
  Set<T> getValidValues();
  
  @UML(identifier = "GC_ParameterInfo.defaultValue", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  T getDefaultValue();
  
  @UML(identifier = "GC_ParameterInfo.minimumValue", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  Comparable<T> getMinimumValue();
  
  @UML(identifier = "GC_ParameterInfo.maximumValue", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  Comparable<T> getMaximumValue();
  
  Unit<?> getUnit();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\ParameterDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */