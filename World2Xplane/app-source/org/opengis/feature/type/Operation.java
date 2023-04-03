package org.opengis.feature.type;

import java.lang.reflect.InvocationTargetException;
import org.opengis.feature.Attribute;

public interface Operation extends PropertyDescriptor {
  int getMaxOccurs();
  
  int getMinOccurs();
  
  OperationType getType();
  
  boolean isImplemented();
  
  Object invoke(Attribute paramAttribute, Object[] paramArrayOfObject) throws InvocationTargetException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\Operation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */