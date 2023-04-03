/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.impl.CoordinateArraySequenceFactory;
/*     */ import com.vividsolutions.jts.geom.util.GeometryEditor;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class GeometryFactory implements Serializable {
/*     */   private static final long serialVersionUID = -6820524753094095635L;
/*     */   
/*     */   private PrecisionModel precisionModel;
/*     */   
/*     */   private CoordinateSequenceFactory coordinateSequenceFactory;
/*     */   
/*     */   private int SRID;
/*     */   
/*     */   public static Point createPointFromInternalCoord(Coordinate coord, Geometry exemplar) {
/*  65 */     exemplar.getPrecisionModel().makePrecise(coord);
/*  66 */     return exemplar.getFactory().createPoint(coord);
/*     */   }
/*     */   
/*     */   public GeometryFactory(PrecisionModel precisionModel, int SRID, CoordinateSequenceFactory coordinateSequenceFactory) {
/*  75 */     this.precisionModel = precisionModel;
/*  76 */     this.coordinateSequenceFactory = coordinateSequenceFactory;
/*  77 */     this.SRID = SRID;
/*     */   }
/*     */   
/*     */   public GeometryFactory(CoordinateSequenceFactory coordinateSequenceFactory) {
/*  86 */     this(new PrecisionModel(), 0, coordinateSequenceFactory);
/*     */   }
/*     */   
/*     */   public GeometryFactory(PrecisionModel precisionModel) {
/*  97 */     this(precisionModel, 0, getDefaultCoordinateSequenceFactory());
/*     */   }
/*     */   
/*     */   public GeometryFactory(PrecisionModel precisionModel, int SRID) {
/* 109 */     this(precisionModel, SRID, getDefaultCoordinateSequenceFactory());
/*     */   }
/*     */   
/*     */   public GeometryFactory() {
/* 117 */     this(new PrecisionModel(), 0);
/*     */   }
/*     */   
/*     */   private static CoordinateSequenceFactory getDefaultCoordinateSequenceFactory() {
/* 122 */     return (CoordinateSequenceFactory)CoordinateArraySequenceFactory.instance();
/*     */   }
/*     */   
/*     */   public static Point[] toPointArray(Collection points) {
/* 132 */     Point[] pointArray = new Point[points.size()];
/* 133 */     return (Point[])points.toArray((Object[])pointArray);
/*     */   }
/*     */   
/*     */   public static Geometry[] toGeometryArray(Collection geometries) {
/* 143 */     if (geometries == null)
/* 143 */       return null; 
/* 144 */     Geometry[] geometryArray = new Geometry[geometries.size()];
/* 145 */     return (Geometry[])geometries.toArray((Object[])geometryArray);
/*     */   }
/*     */   
/*     */   public static LinearRing[] toLinearRingArray(Collection linearRings) {
/* 155 */     LinearRing[] linearRingArray = new LinearRing[linearRings.size()];
/* 156 */     return (LinearRing[])linearRings.toArray((Object[])linearRingArray);
/*     */   }
/*     */   
/*     */   public static LineString[] toLineStringArray(Collection lineStrings) {
/* 166 */     LineString[] lineStringArray = new LineString[lineStrings.size()];
/* 167 */     return (LineString[])lineStrings.toArray((Object[])lineStringArray);
/*     */   }
/*     */   
/*     */   public static Polygon[] toPolygonArray(Collection polygons) {
/* 177 */     Polygon[] polygonArray = new Polygon[polygons.size()];
/* 178 */     return (Polygon[])polygons.toArray((Object[])polygonArray);
/*     */   }
/*     */   
/*     */   public static MultiPolygon[] toMultiPolygonArray(Collection multiPolygons) {
/* 188 */     MultiPolygon[] multiPolygonArray = new MultiPolygon[multiPolygons.size()];
/* 189 */     return (MultiPolygon[])multiPolygons.toArray((Object[])multiPolygonArray);
/*     */   }
/*     */   
/*     */   public static MultiLineString[] toMultiLineStringArray(Collection multiLineStrings) {
/* 199 */     MultiLineString[] multiLineStringArray = new MultiLineString[multiLineStrings.size()];
/* 200 */     return (MultiLineString[])multiLineStrings.toArray((Object[])multiLineStringArray);
/*     */   }
/*     */   
/*     */   public static MultiPoint[] toMultiPointArray(Collection multiPoints) {
/* 210 */     MultiPoint[] multiPointArray = new MultiPoint[multiPoints.size()];
/* 211 */     return (MultiPoint[])multiPoints.toArray((Object[])multiPointArray);
/*     */   }
/*     */   
/*     */   public Geometry toGeometry(Envelope envelope) {
/* 236 */     if (envelope.isNull())
/* 237 */       return createPoint((CoordinateSequence)null); 
/* 241 */     if (envelope.getMinX() == envelope.getMaxX() && envelope.getMinY() == envelope.getMaxY())
/* 242 */       return createPoint(new Coordinate(envelope.getMinX(), envelope.getMinY())); 
/* 246 */     if (envelope.getMinX() == envelope.getMaxX() || envelope.getMinY() == envelope.getMaxY())
/* 248 */       return createLineString(new Coordinate[] { new Coordinate(envelope.getMinX(), envelope.getMinY()), new Coordinate(envelope.getMaxX(), envelope.getMaxY()) }); 
/* 255 */     return createPolygon(createLinearRing(new Coordinate[] { new Coordinate(envelope.getMinX(), envelope.getMinY()), new Coordinate(envelope.getMinX(), envelope.getMaxY()), new Coordinate(envelope.getMaxX(), envelope.getMaxY()), new Coordinate(envelope.getMaxX(), envelope.getMinY()), new Coordinate(envelope.getMinX(), envelope.getMinY()) }), null);
/*     */   }
/*     */   
/*     */   public PrecisionModel getPrecisionModel() {
/* 271 */     return this.precisionModel;
/*     */   }
/*     */   
/*     */   public Point createPoint(Coordinate coordinate) {
/* 282 */     return createPoint((coordinate != null) ? getCoordinateSequenceFactory().create(new Coordinate[] { coordinate }) : null);
/*     */   }
/*     */   
/*     */   public Point createPoint(CoordinateSequence coordinates) {
/* 293 */     return new Point(coordinates, this);
/*     */   }
/*     */   
/*     */   public MultiLineString createMultiLineString(LineString[] lineStrings) {
/* 304 */     return new MultiLineString(lineStrings, this);
/*     */   }
/*     */   
/*     */   public GeometryCollection createGeometryCollection(Geometry[] geometries) {
/* 315 */     return new GeometryCollection(geometries, this);
/*     */   }
/*     */   
/*     */   public MultiPolygon createMultiPolygon(Polygon[] polygons) {
/* 330 */     return new MultiPolygon(polygons, this);
/*     */   }
/*     */   
/*     */   public LinearRing createLinearRing(Coordinate[] coordinates) {
/* 342 */     return createLinearRing((coordinates != null) ? getCoordinateSequenceFactory().create(coordinates) : null);
/*     */   }
/*     */   
/*     */   public LinearRing createLinearRing(CoordinateSequence coordinates) {
/* 355 */     return new LinearRing(coordinates, this);
/*     */   }
/*     */   
/*     */   public MultiPoint createMultiPoint(Point[] point) {
/* 366 */     return new MultiPoint(point, this);
/*     */   }
/*     */   
/*     */   public MultiPoint createMultiPoint(Coordinate[] coordinates) {
/* 377 */     return createMultiPoint((coordinates != null) ? getCoordinateSequenceFactory().create(coordinates) : null);
/*     */   }
/*     */   
/*     */   public MultiPoint createMultiPoint(CoordinateSequence coordinates) {
/* 391 */     if (coordinates == null)
/* 392 */       return createMultiPoint(new Point[0]); 
/* 394 */     Point[] points = new Point[coordinates.size()];
/* 395 */     for (int i = 0; i < coordinates.size(); i++) {
/* 396 */       CoordinateSequence ptSeq = getCoordinateSequenceFactory().create(1, coordinates.getDimension());
/* 398 */       CoordinateSequences.copy(coordinates, i, ptSeq, 0, 1);
/* 399 */       points[i] = createPoint(ptSeq);
/*     */     } 
/* 401 */     return createMultiPoint(points);
/*     */   }
/*     */   
/*     */   public Polygon createPolygon(LinearRing shell, LinearRing[] holes) {
/* 419 */     return new Polygon(shell, holes, this);
/*     */   }
/*     */   
/*     */   public Polygon createPolygon(CoordinateSequence coordinates) {
/* 432 */     return createPolygon(createLinearRing(coordinates));
/*     */   }
/*     */   
/*     */   public Polygon createPolygon(Coordinate[] coordinates) {
/* 445 */     return createPolygon(createLinearRing(coordinates));
/*     */   }
/*     */   
/*     */   public Polygon createPolygon(LinearRing shell) {
/* 458 */     return createPolygon(shell, null);
/*     */   }
/*     */   
/*     */   public Geometry buildGeometry(Collection<Geometry> geomList) {
/* 493 */     Class<?> geomClass = null;
/* 494 */     boolean isHeterogeneous = false;
/* 495 */     boolean hasGeometryCollection = false;
/* 496 */     for (Iterator<Geometry> i = geomList.iterator(); i.hasNext(); ) {
/* 497 */       Geometry geom = i.next();
/* 498 */       Class<?> partClass = geom.getClass();
/* 499 */       if (geomClass == null)
/* 500 */         geomClass = partClass; 
/* 502 */       if (partClass != geomClass)
/* 503 */         isHeterogeneous = true; 
/* 505 */       if (geom instanceof GeometryCollection)
/* 506 */         hasGeometryCollection = true; 
/*     */     } 
/* 513 */     if (geomClass == null)
/* 514 */       return createGeometryCollection(null); 
/* 516 */     if (isHeterogeneous || hasGeometryCollection)
/* 517 */       return createGeometryCollection(toGeometryArray(geomList)); 
/* 522 */     Geometry geom0 = geomList.iterator().next();
/* 523 */     boolean isCollection = (geomList.size() > 1);
/* 524 */     if (isCollection) {
/* 525 */       if (geom0 instanceof Polygon)
/* 526 */         return createMultiPolygon(toPolygonArray(geomList)); 
/* 528 */       if (geom0 instanceof LineString)
/* 529 */         return createMultiLineString(toLineStringArray(geomList)); 
/* 531 */       if (geom0 instanceof Point)
/* 532 */         return createMultiPoint(toPointArray(geomList)); 
/* 534 */       Assert.shouldNeverReachHere("Unhandled class: " + geom0.getClass().getName());
/*     */     } 
/* 536 */     return geom0;
/*     */   }
/*     */   
/*     */   public LineString createLineString(Coordinate[] coordinates) {
/* 546 */     return createLineString((coordinates != null) ? getCoordinateSequenceFactory().create(coordinates) : null);
/*     */   }
/*     */   
/*     */   public LineString createLineString(CoordinateSequence coordinates) {
/* 555 */     return new LineString(coordinates, this);
/*     */   }
/*     */   
/*     */   public Geometry createGeometry(Geometry g) {
/* 577 */     GeometryEditor editor = new GeometryEditor(this);
/* 578 */     return editor.edit(g, (GeometryEditor.GeometryEditorOperation)new GeometryEditor.CoordinateSequenceOperation() {
/*     */           public CoordinateSequence edit(CoordinateSequence coordSeq, Geometry geometry) {
/* 580 */             return GeometryFactory.this.coordinateSequenceFactory.create(coordSeq);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public int getSRID() {
/* 591 */     return this.SRID;
/*     */   }
/*     */   
/*     */   public CoordinateSequenceFactory getCoordinateSequenceFactory() {
/* 597 */     return this.coordinateSequenceFactory;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\GeometryFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */