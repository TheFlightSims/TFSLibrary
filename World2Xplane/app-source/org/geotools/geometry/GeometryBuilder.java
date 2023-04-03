/*     */ package org.geotools.geometry;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.Envelope;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*     */ import org.opengis.geometry.PositionFactory;
/*     */ import org.opengis.geometry.Precision;
/*     */ import org.opengis.geometry.aggregate.AggregateFactory;
/*     */ import org.opengis.geometry.aggregate.MultiCurve;
/*     */ import org.opengis.geometry.aggregate.MultiPoint;
/*     */ import org.opengis.geometry.aggregate.MultiPrimitive;
/*     */ import org.opengis.geometry.aggregate.MultiSurface;
/*     */ import org.opengis.geometry.complex.ComplexFactory;
/*     */ import org.opengis.geometry.complex.CompositeCurve;
/*     */ import org.opengis.geometry.complex.CompositePoint;
/*     */ import org.opengis.geometry.complex.CompositeSurface;
/*     */ import org.opengis.geometry.coordinate.GeometryFactory;
/*     */ import org.opengis.geometry.coordinate.LineSegment;
/*     */ import org.opengis.geometry.coordinate.LineString;
/*     */ import org.opengis.geometry.coordinate.PointArray;
/*     */ import org.opengis.geometry.coordinate.Polygon;
/*     */ import org.opengis.geometry.coordinate.PolyhedralSurface;
/*     */ import org.opengis.geometry.coordinate.Position;
/*     */ import org.opengis.geometry.coordinate.Tin;
/*     */ import org.opengis.geometry.primitive.Curve;
/*     */ import org.opengis.geometry.primitive.OrientableCurve;
/*     */ import org.opengis.geometry.primitive.Point;
/*     */ import org.opengis.geometry.primitive.Primitive;
/*     */ import org.opengis.geometry.primitive.PrimitiveFactory;
/*     */ import org.opengis.geometry.primitive.Ring;
/*     */ import org.opengis.geometry.primitive.Solid;
/*     */ import org.opengis.geometry.primitive.SolidBoundary;
/*     */ import org.opengis.geometry.primitive.Surface;
/*     */ import org.opengis.geometry.primitive.SurfaceBoundary;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ 
/*     */ public class GeometryBuilder {
/*     */   private Hints hints;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*     */   private PositionFactory positionFactory;
/*     */   
/*     */   private PrimitiveFactory primitiveFactory;
/*     */   
/*     */   private AggregateFactory aggregateFactory;
/*     */   
/*     */   private ComplexFactory complexFactory;
/*     */   
/*     */   private GeometryFactory geometryFactory;
/*     */   
/*     */   public GeometryBuilder(CoordinateReferenceSystem crs) {
/* 104 */     this.crs = crs;
/* 105 */     this.hints = GeoTools.getDefaultHints();
/* 106 */     this.hints.put(Hints.CRS, crs);
/* 107 */     this.hints.put(Hints.GEOMETRY_VALIDATE, Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public GeometryBuilder(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 111 */     this(CRS.decode(code));
/*     */   }
/*     */   
/*     */   public GeometryBuilder(Hints hints) {
/* 115 */     this.crs = (CoordinateReferenceSystem)hints.get(Hints.CRS);
/* 116 */     this.hints = hints;
/* 117 */     getPositionFactory();
/* 118 */     getPrecision();
/* 119 */     getPrimitiveFactory();
/* 120 */     getAggregateFactory();
/* 121 */     getGeometryFactory();
/* 122 */     getComplexFactory();
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 126 */     return this.crs;
/*     */   }
/*     */   
/*     */   public void setCoordianteReferenceSystem(CoordinateReferenceSystem crs) {
/* 130 */     if (this.crs != crs) {
/* 131 */       this.hints.remove(Hints.CRS);
/* 132 */       this.hints.put(Hints.CRS, crs);
/* 133 */       this.crs = crs;
/* 134 */       this.positionFactory = null;
/* 135 */       this.primitiveFactory = null;
/* 136 */       this.aggregateFactory = null;
/* 137 */       this.complexFactory = null;
/* 138 */       this.geometryFactory = null;
/* 139 */       getPositionFactory();
/* 140 */       getPrecision();
/* 141 */       getPrimitiveFactory();
/* 142 */       getAggregateFactory();
/* 143 */       getGeometryFactory();
/* 144 */       getComplexFactory();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Precision getPrecision() {
/* 149 */     return getPositionFactory().getPrecision();
/*     */   }
/*     */   
/*     */   public PositionFactory getPositionFactory() {
/* 153 */     if (this.positionFactory == null) {
/* 154 */       if (this.hints.containsKey(Hints.POSITION_FACTORY)) {
/* 156 */         Object factory = this.hints.get(Hints.POSITION_FACTORY);
/* 157 */         if (factory instanceof PositionFactory) {
/* 158 */           this.positionFactory = (PositionFactory)factory;
/* 159 */           return this.positionFactory;
/*     */         } 
/*     */       } 
/* 162 */       this.positionFactory = GeometryFactoryFinder.getPositionFactory(this.hints);
/*     */     } 
/* 164 */     return this.positionFactory;
/*     */   }
/*     */   
/*     */   public PrimitiveFactory getPrimitiveFactory() {
/* 168 */     if (this.primitiveFactory == null) {
/* 169 */       if (this.hints.containsKey(Hints.PRIMITIVE_FACTORY)) {
/* 171 */         Object factory = this.hints.get(Hints.PRIMITIVE_FACTORY);
/* 172 */         if (factory instanceof PrimitiveFactory) {
/* 173 */           this.primitiveFactory = (PrimitiveFactory)factory;
/* 174 */           return this.primitiveFactory;
/*     */         } 
/*     */       } 
/* 177 */       this.primitiveFactory = GeometryFactoryFinder.getPrimitiveFactory(this.hints);
/*     */     } 
/* 179 */     return this.primitiveFactory;
/*     */   }
/*     */   
/*     */   public AggregateFactory getAggregateFactory() {
/* 183 */     if (this.aggregateFactory == null) {
/* 184 */       if (this.hints.containsKey(Hints.AGGREGATE_FACTORY)) {
/* 186 */         Object factory = this.hints.get(Hints.AGGREGATE_FACTORY);
/* 187 */         if (factory instanceof AggregateFactory) {
/* 188 */           this.aggregateFactory = (AggregateFactory)factory;
/* 189 */           return this.aggregateFactory;
/*     */         } 
/*     */       } 
/* 192 */       this.aggregateFactory = GeometryFactoryFinder.getAggregateFactory(this.hints);
/*     */     } 
/* 194 */     return this.aggregateFactory;
/*     */   }
/*     */   
/*     */   public GeometryFactory getGeometryFactory() {
/* 198 */     if (this.geometryFactory == null) {
/* 199 */       if (this.hints.containsKey(Hints.GEOMETRY_FACTORY)) {
/* 201 */         Object factory = this.hints.get(Hints.GEOMETRY_FACTORY);
/* 202 */         if (factory instanceof GeometryFactory) {
/* 203 */           this.geometryFactory = (GeometryFactory)factory;
/* 204 */           return this.geometryFactory;
/*     */         } 
/*     */       } 
/* 207 */       this.geometryFactory = GeometryFactoryFinder.getGeometryFactory(this.hints);
/*     */     } 
/* 209 */     return this.geometryFactory;
/*     */   }
/*     */   
/*     */   public ComplexFactory getComplexFactory() {
/* 213 */     if (this.complexFactory == null) {
/* 214 */       if (this.hints.containsKey(Hints.COMPLEX_FACTORY)) {
/* 216 */         Object factory = this.hints.get(Hints.COMPLEX_FACTORY);
/* 217 */         if (factory instanceof ComplexFactory) {
/* 218 */           this.complexFactory = (ComplexFactory)factory;
/* 219 */           return this.complexFactory;
/*     */         } 
/*     */       } 
/* 222 */       this.complexFactory = GeometryFactoryFinder.getComplexFactory(this.hints);
/*     */     } 
/* 224 */     return this.complexFactory;
/*     */   }
/*     */   
/*     */   public DirectPosition createDirectPosition(double[] ordinates) {
/* 228 */     return getPositionFactory().createDirectPosition(ordinates);
/*     */   }
/*     */   
/*     */   public Position createPosition(Position position) {
/* 232 */     return getPositionFactory().createPosition(position);
/*     */   }
/*     */   
/*     */   public PointArray createPointArray() {
/* 236 */     return getPositionFactory().createPointArray();
/*     */   }
/*     */   
/*     */   public PointArray createPointArray(double[] array) {
/* 240 */     return getPositionFactory().createPointArray(array, 0, array.length / this.crs.getCoordinateSystem().getDimension());
/*     */   }
/*     */   
/*     */   public PointArray createPointArray(double[] array, int start, int end) {
/* 243 */     return getPositionFactory().createPointArray(array, start, end);
/*     */   }
/*     */   
/*     */   public PointArray createPositionList(float[] array, int start, int end) {
/* 247 */     return getPositionFactory().createPointArray(array, start, end);
/*     */   }
/*     */   
/*     */   public Curve createCurve(List segments) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 251 */     if (segments == null)
/* 252 */       throw new NullPointerException("Segments are required to create a curve"); 
/* 257 */     return getPrimitiveFactory().createCurve(segments);
/*     */   }
/*     */   
/*     */   public Curve createCurve(PointArray points) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 260 */     if (points == null)
/* 261 */       throw new NullPointerException("Points are required to create a curve"); 
/* 266 */     List<LineSegment> segmentList = new ArrayList();
/* 267 */     for (int i = 0; i < points.size(); i++) {
/* 268 */       int start = i;
/* 269 */       int end = (i + 1) % points.size();
/* 270 */       DirectPosition point1 = points.getDirectPosition(start, null);
/* 271 */       DirectPosition point2 = points.getDirectPosition(end, null);
/* 272 */       LineSegment segment = createLineSegment(point1, point2);
/* 273 */       segmentList.add(segment);
/*     */     } 
/* 275 */     return getPrimitiveFactory().createCurve(segmentList);
/*     */   }
/*     */   
/*     */   public Point createPoint(double ord1, double ord2) {
/* 285 */     return createPoint(new double[] { ord1, ord2 });
/*     */   }
/*     */   
/*     */   public Point createPoint(double ord1, double ord2, double ord3) {
/* 295 */     return createPoint(new double[] { ord1, ord2, ord3 });
/*     */   }
/*     */   
/*     */   public Point createPoint(double[] ordinates) throws MismatchedDimensionException {
/* 304 */     if (ordinates == null)
/* 305 */       throw new NullPointerException("Ordinates required to create a point"); 
/* 306 */     int dimension = getCoordinateReferenceSystem().getCoordinateSystem().getDimension();
/* 307 */     if (ordinates.length != dimension)
/* 308 */       throw new MismatchedDimensionException("Create point requires " + dimension + " ordinates (" + ordinates.length + " provided"); 
/* 310 */     return getPrimitiveFactory().createPoint(ordinates);
/*     */   }
/*     */   
/*     */   public Point createPoint(Position position) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 314 */     if (position == null)
/* 315 */       throw new NullPointerException(); 
/* 317 */     setCoordianteReferenceSystem(position.getDirectPosition().getCoordinateReferenceSystem());
/* 318 */     DirectPosition copy = getPositionFactory().createDirectPosition(position.getDirectPosition().getCoordinate());
/* 319 */     return getPrimitiveFactory().createPoint((Position)copy);
/*     */   }
/*     */   
/*     */   public Primitive createPrimitive(Envelope envelope) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 323 */     LineSegment segment = processBoundsToSegment(envelope);
/* 324 */     setCoordianteReferenceSystem(envelope.getCoordinateReferenceSystem());
/* 325 */     return processSegmentToPrimitive(envelope, segment, 1);
/*     */   }
/*     */   
/*     */   private Primitive processSegmentToPrimitive(Envelope bounds, LineSegment segment, int dimension) {
/* 329 */     CoordinateSystemAxis axis = this.crs.getCoordinateSystem().getAxis(dimension);
/* 331 */     if (axis.getDirection() == AxisDirection.OTHER)
/* 332 */       return processSegmentToPrimitive(bounds, segment, dimension + 1); 
/* 334 */     Ring ring = processBoundsToRing(bounds, segment, dimension);
/* 335 */     return processRingToPrimitive(bounds, ring, dimension + 1);
/*     */   }
/*     */   
/*     */   private Ring processBoundsToRing(Envelope bounds, LineSegment segment, int D) {
/* 339 */     DirectPosition one = getPositionFactory().createDirectPosition(segment.getStartPoint().getCoordinate());
/* 340 */     one.setOrdinate(D, bounds.getMinimum(D));
/* 342 */     DirectPosition two = getPositionFactory().createDirectPosition(segment.getEndPoint().getCoordinate());
/* 343 */     two.setOrdinate(D, bounds.getMinimum(D));
/* 345 */     DirectPosition three = getPositionFactory().createDirectPosition(two.getCoordinate());
/* 346 */     three.setOrdinate(D, bounds.getMaximum(D));
/* 348 */     DirectPosition four = getPositionFactory().createDirectPosition(one.getCoordinate());
/* 349 */     four.setOrdinate(D, bounds.getMaximum(D));
/* 351 */     LineSegment edge1 = getGeometryFactory().createLineSegment((Position)one, (Position)two);
/* 352 */     LineSegment edge2 = getGeometryFactory().createLineSegment((Position)two, (Position)three);
/* 353 */     LineSegment edge3 = getGeometryFactory().createLineSegment((Position)three, (Position)four);
/* 354 */     LineSegment edge4 = getGeometryFactory().createLineSegment((Position)four, (Position)one);
/* 356 */     List<OrientableCurve> edges = new ArrayList<OrientableCurve>();
/* 357 */     edges.add(createCurve(Arrays.asList(new LineSegment[] { edge1 })));
/* 358 */     edges.add(createCurve(Arrays.asList(new LineSegment[] { edge2 })));
/* 359 */     edges.add(createCurve(Arrays.asList(new LineSegment[] { edge3 })));
/* 360 */     edges.add(createCurve(Arrays.asList(new LineSegment[] { edge4 })));
/* 361 */     return createRing(edges);
/*     */   }
/*     */   
/*     */   private Primitive processRingToPrimitive(Envelope bounds, Ring ring, int dimension) {
/* 365 */     int D = this.crs.getCoordinateSystem().getDimension();
/* 366 */     if (dimension == D) {
/* 367 */       SurfaceBoundary boundary = createSurfaceBoundary(ring, Collections.EMPTY_LIST);
/* 368 */       return (Primitive)createSurface(boundary);
/*     */     } 
/* 370 */     CoordinateSystemAxis axis = this.crs.getCoordinateSystem().getAxis(dimension);
/* 371 */     if (axis.getDirection() == AxisDirection.OTHER)
/* 372 */       return processRingToPrimitive(bounds, ring, dimension + 1); 
/* 374 */     return processRingToVolumne(bounds, ring, dimension + 1);
/*     */   }
/*     */   
/*     */   private Primitive processRingToVolumne(Envelope bounds, Ring ring, int i) {
/* 379 */     throw new UnsupportedOperationException("Not yet 3D");
/*     */   }
/*     */   
/*     */   private LineSegment processBoundsToSegment(Envelope bounds) {
/* 383 */     int D = 0;
/* 384 */     CoordinateReferenceSystem crs = bounds.getCoordinateReferenceSystem();
/* 385 */     CoordinateSystemAxis axis = crs.getCoordinateSystem().getAxis(0);
/* 387 */     DirectPosition positionA = getPositionFactory().createDirectPosition(null);
/* 388 */     DirectPosition positionB = getPositionFactory().createDirectPosition(null);
/* 389 */     if (axis.getDirection() != AxisDirection.OTHER) {
/* 390 */       positionA.setOrdinate(0, bounds.getMinimum(0));
/* 391 */       positionB.setOrdinate(0, bounds.getMaximum(0));
/*     */     } 
/* 393 */     return getGeometryFactory().createLineSegment((Position)positionA, (Position)positionB);
/*     */   }
/*     */   
/*     */   public Ring createRing(List<OrientableCurve> orientableCurves) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 403 */     return getPrimitiveFactory().createRing(orientableCurves);
/*     */   }
/*     */   
/*     */   public Solid createSolid(SolidBoundary boundary) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 407 */     return getPrimitiveFactory().createSolid(boundary);
/*     */   }
/*     */   
/*     */   public SurfaceBoundary createSurfaceBoundary(PointArray points) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 411 */     Curve curve = createCurve(points);
/* 412 */     return createSurfaceBoundary((OrientableCurve)curve);
/*     */   }
/*     */   
/*     */   public Surface createSurface(List surfaces) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 415 */     return getPrimitiveFactory().createSurface(surfaces);
/*     */   }
/*     */   
/*     */   public Surface createSurface(SurfaceBoundary boundary) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 419 */     return getPrimitiveFactory().createSurface(boundary);
/*     */   }
/*     */   
/*     */   public SurfaceBoundary createSurfaceBoundary(Ring exterior, List interiors) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 423 */     return getPrimitiveFactory().createSurfaceBoundary(exterior, interiors);
/*     */   }
/*     */   
/*     */   public SurfaceBoundary createSurfaceBoundary(Ring exterior) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 427 */     return createSurfaceBoundary(exterior, new ArrayList());
/*     */   }
/*     */   
/*     */   public SurfaceBoundary createSurfaceBoundary(OrientableCurve curve) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 431 */     List<OrientableCurve> exterior = new ArrayList<OrientableCurve>(1);
/* 432 */     exterior.add(curve);
/* 433 */     Ring ring = createRing(exterior);
/* 434 */     return createSurfaceBoundary(ring);
/*     */   }
/*     */   
/*     */   public DirectPosition createDirectPosition() {
/* 485 */     return createDirectPosition(null);
/*     */   }
/*     */   
/*     */   public Envelope createEnvelope(DirectPosition lowerCorner, DirectPosition upperCorner) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 489 */     return getGeometryFactory().createEnvelope(lowerCorner, upperCorner);
/*     */   }
/*     */   
/*     */   public LineSegment createLineSegment(Position startPoint, Position endPoint) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 493 */     return getGeometryFactory().createLineSegment(startPoint, endPoint);
/*     */   }
/*     */   
/*     */   public LineString createLineString(List points) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 497 */     return getGeometryFactory().createLineString(points);
/*     */   }
/*     */   
/*     */   public LineString createLineString(PointArray points) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 500 */     return getGeometryFactory().createLineString((List)points);
/*     */   }
/*     */   
/*     */   public LineSegment createLineSegment(DirectPosition from, DirectPosition to) {
/* 503 */     return getGeometryFactory().createLineSegment((Position)from, (Position)to);
/*     */   }
/*     */   
/*     */   public MultiPrimitive createMultiPrimitive() {
/* 506 */     return getGeometryFactory().createMultiPrimitive();
/*     */   }
/*     */   
/*     */   public Polygon createPolygon(SurfaceBoundary boundary) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 510 */     return getGeometryFactory().createPolygon(boundary);
/*     */   }
/*     */   
/*     */   public Polygon createPolygon(SurfaceBoundary boundary, Surface spanSurface) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 514 */     return getGeometryFactory().createPolygon(boundary, spanSurface);
/*     */   }
/*     */   
/*     */   public PolyhedralSurface createPolyhedralSurface(List tiles) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 518 */     return getGeometryFactory().createPolyhedralSurface(tiles);
/*     */   }
/*     */   
/*     */   public Tin createTin(Set post, Set stopLines, Set breakLines, double maxLength) throws MismatchedReferenceSystemException, MismatchedDimensionException {
/* 522 */     return getGeometryFactory().createTin(post, stopLines, breakLines, maxLength);
/*     */   }
/*     */   
/*     */   public CompositeCurve createCompositeCurve(List generator) {
/* 526 */     return getComplexFactory().createCompositeCurve(generator);
/*     */   }
/*     */   
/*     */   public CompositePoint createCompositePoint(Point generator) {
/* 530 */     return getComplexFactory().createCompositePoint(generator);
/*     */   }
/*     */   
/*     */   public CompositeSurface createCompositeSurface(List generator) {
/* 534 */     return getComplexFactory().createCompositeSurface(generator);
/*     */   }
/*     */   
/*     */   public MultiCurve createMultiCurve(Set curves) {
/* 538 */     return getAggregateFactory().createMultiCurve(curves);
/*     */   }
/*     */   
/*     */   public MultiPoint createMultiPoint(Set points) {
/* 542 */     return getAggregateFactory().createMultiPoint(points);
/*     */   }
/*     */   
/*     */   public MultiPrimitive createMultiPrimitive(Set primitives) {
/* 546 */     return getAggregateFactory().createMultiPrimitive(primitives);
/*     */   }
/*     */   
/*     */   public MultiSurface createMultiSurface(Set surfaces) {
/* 550 */     return getAggregateFactory().createMultiSurface(surfaces);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\GeometryBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */