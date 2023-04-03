package org.opengis.geometry.primitive;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.geometry.MismatchedReferenceSystemException;
import org.opengis.geometry.coordinate.Position;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface PrimitiveFactory {
  CoordinateReferenceSystem getCoordinateReferenceSystem();
  
  @UML(identifier = "GM_Primitive(GM_Envelope)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Primitive createPrimitive(Envelope paramEnvelope) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  Point createPoint(double[] paramArrayOfdouble) throws MismatchedDimensionException;
  
  @UML(identifier = "GM_Point(GM_Position)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Point createPoint(Position paramPosition) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_Curve(GM_CurveSegment[1..n])", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Curve createCurve(List<CurveSegment> paramList) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_Surface(GM_SurfacePatch[1..n])", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Surface createSurface(List<SurfacePatch> paramList) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_Surface(GM_SurfaceBoundary)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Surface createSurface(SurfaceBoundary paramSurfaceBoundary) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  SurfaceBoundary createSurfaceBoundary(Ring paramRing, List<Ring> paramList) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_Solid(GM_SolidBoundary)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Solid createSolid(SolidBoundary paramSolidBoundary) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  Ring createRing(List<OrientableCurve> paramList) throws MismatchedReferenceSystemException, MismatchedDimensionException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\PrimitiveFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */