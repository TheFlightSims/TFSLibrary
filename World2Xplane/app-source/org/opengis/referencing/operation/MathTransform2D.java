package org.opengis.referencing.operation;

import java.awt.Shape;
import java.awt.geom.Point2D;

public interface MathTransform2D extends MathTransform {
  Point2D transform(Point2D paramPoint2D1, Point2D paramPoint2D2) throws TransformException;
  
  Shape createTransformedShape(Shape paramShape) throws TransformException;
  
  Matrix derivative(Point2D paramPoint2D) throws TransformException;
  
  MathTransform2D inverse() throws NoninvertibleTransformException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\MathTransform2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */