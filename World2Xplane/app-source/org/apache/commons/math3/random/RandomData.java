package org.apache.commons.math3.random;

import java.util.Collection;

public interface RandomData {
  String nextHexString(int paramInt);
  
  int nextInt(int paramInt1, int paramInt2);
  
  long nextLong(long paramLong1, long paramLong2);
  
  String nextSecureHexString(int paramInt);
  
  int nextSecureInt(int paramInt1, int paramInt2);
  
  long nextSecureLong(long paramLong1, long paramLong2);
  
  long nextPoisson(double paramDouble);
  
  double nextGaussian(double paramDouble1, double paramDouble2);
  
  double nextExponential(double paramDouble);
  
  double nextUniform(double paramDouble1, double paramDouble2);
  
  double nextUniform(double paramDouble1, double paramDouble2, boolean paramBoolean);
  
  int[] nextPermutation(int paramInt1, int paramInt2);
  
  Object[] nextSample(Collection<?> paramCollection, int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\RandomData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */