package org.opengis.referencing.operation;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;

@UML(identifier = "CT_MathTransform", specification = Specification.OGC_01009)
public interface MathTransform {
  @UML(identifier = "getDimSource", specification = Specification.OGC_01009)
  int getSourceDimensions();
  
  @UML(identifier = "getDimTarget", specification = Specification.OGC_01009)
  int getTargetDimensions();
  
  @UML(identifier = "transform", specification = Specification.OGC_01009)
  DirectPosition transform(DirectPosition paramDirectPosition1, DirectPosition paramDirectPosition2) throws MismatchedDimensionException, TransformException;
  
  @UML(identifier = "transformList", specification = Specification.OGC_01009)
  void transform(double[] paramArrayOfdouble1, int paramInt1, double[] paramArrayOfdouble2, int paramInt2, int paramInt3) throws TransformException;
  
  void transform(float[] paramArrayOffloat1, int paramInt1, float[] paramArrayOffloat2, int paramInt2, int paramInt3) throws TransformException;
  
  void transform(float[] paramArrayOffloat, int paramInt1, double[] paramArrayOfdouble, int paramInt2, int paramInt3) throws TransformException;
  
  void transform(double[] paramArrayOfdouble, int paramInt1, float[] paramArrayOffloat, int paramInt2, int paramInt3) throws TransformException;
  
  @UML(identifier = "derivative", specification = Specification.OGC_01009)
  Matrix derivative(DirectPosition paramDirectPosition) throws MismatchedDimensionException, TransformException;
  
  @UML(identifier = "inverse", specification = Specification.OGC_01009)
  MathTransform inverse() throws NoninvertibleTransformException;
  
  @UML(identifier = "isIdentity", specification = Specification.OGC_01009)
  boolean isIdentity();
  
  @UML(identifier = "getWKT", specification = Specification.OGC_01009)
  String toWKT() throws UnsupportedOperationException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\MathTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */