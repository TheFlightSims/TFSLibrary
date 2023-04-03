package org.opengis.geometry;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.complex.Complex;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

@UML(identifier = "GM_Object", specification = Specification.ISO_19107)
public interface Geometry extends TransfiniteSet {
  @UML(identifier = "CRS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CoordinateReferenceSystem getCoordinateReferenceSystem();
  
  Precision getPrecision();
  
  @UML(identifier = "mbRegion", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Geometry getMbRegion();
  
  @UML(identifier = "representativePoint", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition getRepresentativePoint();
  
  @UML(identifier = "boundary", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Boundary getBoundary();
  
  @UML(identifier = "closure", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Complex getClosure();
  
  @UML(identifier = "isSimple", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  boolean isSimple();
  
  @UML(identifier = "isCycle", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  boolean isCycle();
  
  @UML(identifier = "distance", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double distance(Geometry paramGeometry);
  
  @UML(identifier = "dimension", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getDimension(DirectPosition paramDirectPosition);
  
  @UML(identifier = "coordinateDimension", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getCoordinateDimension();
  
  @UML(identifier = "maximalComplex", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<? extends Complex> getMaximalComplex();
  
  @UML(identifier = "transform", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Geometry transform(CoordinateReferenceSystem paramCoordinateReferenceSystem) throws TransformException;
  
  Geometry transform(CoordinateReferenceSystem paramCoordinateReferenceSystem, MathTransform paramMathTransform) throws TransformException;
  
  @UML(identifier = "envelope", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Envelope getEnvelope();
  
  @UML(identifier = "centroid", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition getCentroid();
  
  @UML(identifier = "convexHull", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Geometry getConvexHull();
  
  @UML(identifier = "buffer", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Geometry getBuffer(double paramDouble);
  
  boolean isMutable();
  
  Geometry toImmutable();
  
  Geometry clone() throws CloneNotSupportedException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\Geometry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */