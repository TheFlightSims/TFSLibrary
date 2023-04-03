package org.apache.commons.math3;

public interface Field<T> {
  T getZero();
  
  T getOne();
  
  Class<? extends FieldElement<T>> getRuntimeClass();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\Field.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */