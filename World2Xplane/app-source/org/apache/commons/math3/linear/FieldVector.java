package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;

public interface FieldVector<T extends org.apache.commons.math3.FieldElement<T>> {
  Field<T> getField();
  
  FieldVector<T> copy();
  
  FieldVector<T> add(FieldVector<T> paramFieldVector) throws IllegalArgumentException;
  
  FieldVector<T> subtract(FieldVector<T> paramFieldVector) throws IllegalArgumentException;
  
  FieldVector<T> mapAdd(T paramT);
  
  FieldVector<T> mapAddToSelf(T paramT);
  
  FieldVector<T> mapSubtract(T paramT);
  
  FieldVector<T> mapSubtractToSelf(T paramT);
  
  FieldVector<T> mapMultiply(T paramT);
  
  FieldVector<T> mapMultiplyToSelf(T paramT);
  
  FieldVector<T> mapDivide(T paramT);
  
  FieldVector<T> mapDivideToSelf(T paramT);
  
  FieldVector<T> mapInv();
  
  FieldVector<T> mapInvToSelf();
  
  FieldVector<T> ebeMultiply(FieldVector<T> paramFieldVector) throws IllegalArgumentException;
  
  FieldVector<T> ebeDivide(FieldVector<T> paramFieldVector) throws IllegalArgumentException;
  
  T[] getData();
  
  T dotProduct(FieldVector<T> paramFieldVector) throws IllegalArgumentException;
  
  FieldVector<T> projection(FieldVector<T> paramFieldVector) throws IllegalArgumentException;
  
  FieldMatrix<T> outerProduct(FieldVector<T> paramFieldVector);
  
  T getEntry(int paramInt);
  
  void setEntry(int paramInt, T paramT);
  
  int getDimension();
  
  FieldVector<T> append(FieldVector<T> paramFieldVector);
  
  FieldVector<T> append(T paramT);
  
  FieldVector<T> getSubVector(int paramInt1, int paramInt2);
  
  void setSubVector(int paramInt, FieldVector<T> paramFieldVector);
  
  void set(T paramT);
  
  T[] toArray();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\FieldVector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */