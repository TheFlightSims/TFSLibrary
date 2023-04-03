package org.apache.commons.math3.transform;

import org.apache.commons.math3.analysis.UnivariateFunction;

public interface RealTransformer {
  double[] transform(double[] paramArrayOfdouble, TransformType paramTransformType);
  
  double[] transform(UnivariateFunction paramUnivariateFunction, double paramDouble1, double paramDouble2, int paramInt, TransformType paramTransformType);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\transform\RealTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */