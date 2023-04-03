package org.opengis.parameter;

import java.net.URI;
import javax.measure.unit.Unit;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CC_ParameterValue", specification = Specification.ISO_19111)
public interface ParameterValue<T> extends GeneralParameterValue {
  ParameterDescriptor<T> getDescriptor();
  
  Unit<?> getUnit();
  
  double doubleValue(Unit<?> paramUnit) throws InvalidParameterTypeException;
  
  @UML(identifier = "value", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  double doubleValue() throws InvalidParameterTypeException;
  
  @UML(identifier = "integerValue", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  int intValue() throws InvalidParameterTypeException;
  
  @UML(identifier = "booleanValue", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  boolean booleanValue() throws InvalidParameterTypeException;
  
  @UML(identifier = "stringValue", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  String stringValue() throws InvalidParameterTypeException;
  
  double[] doubleValueList(Unit<?> paramUnit) throws InvalidParameterTypeException;
  
  @UML(identifier = "valueList", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  double[] doubleValueList() throws InvalidParameterTypeException;
  
  @UML(identifier = "integerValueList", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  int[] intValueList() throws InvalidParameterTypeException;
  
  @UML(identifier = "valueFile", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  URI valueFile() throws InvalidParameterTypeException;
  
  @UML(identifier = "value", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  T getValue();
  
  void setValue(double[] paramArrayOfdouble, Unit<?> paramUnit) throws InvalidParameterValueException;
  
  void setValue(double paramDouble, Unit<?> paramUnit) throws InvalidParameterValueException;
  
  void setValue(double paramDouble) throws InvalidParameterValueException;
  
  void setValue(int paramInt) throws InvalidParameterValueException;
  
  void setValue(boolean paramBoolean) throws InvalidParameterValueException;
  
  void setValue(Object paramObject) throws InvalidParameterValueException;
  
  ParameterValue clone();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\ParameterValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */