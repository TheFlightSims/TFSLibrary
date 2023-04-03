package org.opengis.geometry.coordinate;

import java.util.List;
import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.geometry.MismatchedReferenceSystemException;
import org.opengis.geometry.aggregate.MultiPrimitive;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfaceBoundary;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface GeometryFactory {
  CoordinateReferenceSystem getCoordinateReferenceSystem();
  
  @Deprecated
  DirectPosition createDirectPosition();
  
  @Deprecated
  DirectPosition createDirectPosition(double[] paramArrayOfdouble);
  
  Envelope createEnvelope(DirectPosition paramDirectPosition1, DirectPosition paramDirectPosition2) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_LineSegment(GM_Position[2])", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  LineSegment createLineSegment(Position paramPosition1, Position paramPosition2) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_LineString(GM_Position[2..n])", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  LineString createLineString(List<Position> paramList) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  Geodesic createGeodesic(Position paramPosition1, Position paramPosition2) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_GeodesicString(GM_Position[2..n])", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  GeodesicString createGeodesicString(List<Position> paramList) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_Arc(GM_Position[3])", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Arc createArc(Position paramPosition1, Position paramPosition2, Position paramPosition3) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_Arc(GM_Position[2],Real,Vector)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Arc createArc(Position paramPosition1, Position paramPosition2, double paramDouble, double[] paramArrayOfdouble) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_ArcString(GM_Position[3, 5, 7...])", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  ArcString createArcString(List<Position> paramList) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_ArcByBulge(GM_Position[2],Real,Vector)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  ArcByBulge createArcByBulge(Position paramPosition1, Position paramPosition2, double paramDouble, double[] paramArrayOfdouble) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_ArcStringByBulge(GM_Position[2..n],Real[1..n],Vector[1..n])", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  ArcStringByBulge createArcStringByBulge(List<Position> paramList, double[] paramArrayOfdouble, List<double[]> paramList1) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_BSplineCurve(Integer,GM_PointArray,Sequence<GM_Knot>,GM_KnotType)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  BSplineCurve createBSplineCurve(int paramInt, PointArray paramPointArray, List<Knot> paramList, KnotType paramKnotType) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_BSplineSurface(Sequence<GM_PointArray>,Integer,Sequence<GM_Knot>,GM_KnotType)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  BSplineSurface createBSplineSurface(List<PointArray> paramList, int[] paramArrayOfint, List<Knot>[] paramArrayOfList, KnotType paramKnotType) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_Polygon(GM_SurfaceBondary)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Polygon createPolygon(SurfaceBoundary paramSurfaceBoundary) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_Polygon(GM_SurfaceBondary,GM_Surface)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Polygon createPolygon(SurfaceBoundary paramSurfaceBoundary, Surface paramSurface) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_Tin(Set<GM_Position>,Set<GM_LineString>,Set<GM_LineString>,Number)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Tin createTin(Set<Position> paramSet, Set<LineString> paramSet1, Set<LineString> paramSet2, double paramDouble) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @UML(identifier = "GM_PolyhedralSurace(GM_Polygon)", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  PolyhedralSurface createPolyhedralSurface(List<Polygon> paramList) throws MismatchedReferenceSystemException, MismatchedDimensionException;
  
  @Deprecated
  MultiPrimitive createMultiPrimitive();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\GeometryFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */