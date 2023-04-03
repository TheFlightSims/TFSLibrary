package org.apache.commons.math3;

public interface FieldElement<T> {
  T add(T paramT);
  
  T subtract(T paramT);
  
  T negate();
  
  T multiply(int paramInt);
  
  T multiply(T paramT);
  
  T divide(T paramT);
  
  T reciprocal();
  
  Field<T> getField();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\FieldElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */