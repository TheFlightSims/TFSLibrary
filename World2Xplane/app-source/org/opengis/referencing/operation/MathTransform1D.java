package org.opengis.referencing.operation;

public interface MathTransform1D extends MathTransform {
  double transform(double paramDouble) throws TransformException;
  
  double derivative(double paramDouble) throws TransformException;
  
  MathTransform1D inverse() throws NoninvertibleTransformException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\MathTransform1D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */