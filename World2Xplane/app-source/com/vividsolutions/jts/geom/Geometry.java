/*      */ package com.vividsolutions.jts.geom;
/*      */ 
/*      */ import com.vividsolutions.jts.algorithm.Centroid;
/*      */ import com.vividsolutions.jts.algorithm.ConvexHull;
/*      */ import com.vividsolutions.jts.algorithm.InteriorPointArea;
/*      */ import com.vividsolutions.jts.algorithm.InteriorPointLine;
/*      */ import com.vividsolutions.jts.algorithm.InteriorPointPoint;
/*      */ import com.vividsolutions.jts.geom.util.GeometryCollectionMapper;
/*      */ import com.vividsolutions.jts.geom.util.GeometryMapper;
/*      */ import com.vividsolutions.jts.io.WKTWriter;
/*      */ import com.vividsolutions.jts.operation.IsSimpleOp;
/*      */ import com.vividsolutions.jts.operation.buffer.BufferOp;
/*      */ import com.vividsolutions.jts.operation.distance.DistanceOp;
/*      */ import com.vividsolutions.jts.operation.overlay.OverlayOp;
/*      */ import com.vividsolutions.jts.operation.overlay.snap.SnapIfNeededOverlayOp;
/*      */ import com.vividsolutions.jts.operation.predicate.RectangleContains;
/*      */ import com.vividsolutions.jts.operation.predicate.RectangleIntersects;
/*      */ import com.vividsolutions.jts.operation.relate.RelateOp;
/*      */ import com.vividsolutions.jts.operation.union.UnaryUnionOp;
/*      */ import com.vividsolutions.jts.operation.valid.IsValidOp;
/*      */ import com.vividsolutions.jts.util.Assert;
/*      */ import java.io.Serializable;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ 
/*      */ public abstract class Geometry implements Cloneable, Comparable, Serializable {
/*      */   private static final long serialVersionUID = 8763622679187376702L;
/*      */   
/*  175 */   private static final Class[] sortedClasses = new Class[] { Point.class, MultiPoint.class, LineString.class, LinearRing.class, MultiLineString.class, Polygon.class, MultiPolygon.class, GeometryCollection.class };
/*      */   
/*  185 */   private static final GeometryComponentFilter geometryChangedFilter = new GeometryComponentFilter() {
/*      */       public void filter(Geometry geom) {
/*  187 */         geom.geometryChangedAction();
/*      */       }
/*      */     };
/*      */   
/*      */   protected Envelope envelope;
/*      */   
/*      */   protected final GeometryFactory factory;
/*      */   
/*      */   protected int SRID;
/*      */   
/*  210 */   private Object userData = null;
/*      */   
/*      */   public Geometry(GeometryFactory factory) {
/*  218 */     this.factory = factory;
/*  219 */     this.SRID = factory.getSRID();
/*      */   }
/*      */   
/*      */   public abstract String getGeometryType();
/*      */   
/*      */   protected static boolean hasNonEmptyElements(Geometry[] geometries) {
/*  238 */     for (int i = 0; i < geometries.length; i++) {
/*  239 */       if (!geometries[i].isEmpty())
/*  240 */         return true; 
/*      */     } 
/*  243 */     return false;
/*      */   }
/*      */   
/*      */   protected static boolean hasNullElements(Object[] array) {
/*  254 */     for (int i = 0; i < array.length; i++) {
/*  255 */       if (array[i] == null)
/*  256 */         return true; 
/*      */     } 
/*  259 */     return false;
/*      */   }
/*      */   
/*      */   public int getSRID() {
/*  277 */     return this.SRID;
/*      */   }
/*      */   
/*      */   public void setSRID(int SRID) {
/*  291 */     this.SRID = SRID;
/*      */   }
/*      */   
/*      */   public GeometryFactory getFactory() {
/*  300 */     return this.factory;
/*      */   }
/*      */   
/*      */   public Object getUserData() {
/*  309 */     return this.userData;
/*      */   }
/*      */   
/*      */   public int getNumGeometries() {
/*  319 */     return 1;
/*      */   }
/*      */   
/*      */   public Geometry getGeometryN(int n) {
/*  330 */     return this;
/*      */   }
/*      */   
/*      */   public void setUserData(Object userData) {
/*  345 */     this.userData = userData;
/*      */   }
/*      */   
/*      */   public PrecisionModel getPrecisionModel() {
/*  356 */     return this.factory.getPrecisionModel();
/*      */   }
/*      */   
/*      */   public abstract Coordinate getCoordinate();
/*      */   
/*      */   public abstract Coordinate[] getCoordinates();
/*      */   
/*      */   public abstract int getNumPoints();
/*      */   
/*      */   public boolean isSimple() {
/*  425 */     IsSimpleOp op = new IsSimpleOp(this);
/*  426 */     return op.isSimple();
/*      */   }
/*      */   
/*      */   public boolean isValid() {
/*  441 */     return IsValidOp.isValid(this);
/*      */   }
/*      */   
/*      */   public abstract boolean isEmpty();
/*      */   
/*      */   public double distance(Geometry g) {
/*  463 */     return DistanceOp.distance(this, g);
/*      */   }
/*      */   
/*      */   public boolean isWithinDistance(Geometry geom, double distance) {
/*  476 */     double envDist = getEnvelopeInternal().distance(geom.getEnvelopeInternal());
/*  477 */     if (envDist > distance)
/*  478 */       return false; 
/*  479 */     return DistanceOp.isWithinDistance(this, geom, distance);
/*      */   }
/*      */   
/*      */   public boolean isRectangle() {
/*  491 */     return false;
/*      */   }
/*      */   
/*      */   public double getArea() {
/*  504 */     return 0.0D;
/*      */   }
/*      */   
/*      */   public double getLength() {
/*  518 */     return 0.0D;
/*      */   }
/*      */   
/*      */   public Point getCentroid() {
/*  534 */     if (isEmpty())
/*  535 */       return this.factory.createPoint((Coordinate)null); 
/*  536 */     Coordinate centPt = Centroid.getCentroid(this);
/*  537 */     return createPointFromInternalCoord(centPt, this);
/*      */   }
/*      */   
/*      */   public Point getInteriorPoint() {
/*  552 */     if (isEmpty())
/*  553 */       return this.factory.createPoint((Coordinate)null); 
/*  554 */     Coordinate interiorPt = null;
/*  555 */     int dim = getDimension();
/*  556 */     if (dim == 0) {
/*  557 */       InteriorPointPoint intPt = new InteriorPointPoint(this);
/*  558 */       interiorPt = intPt.getInteriorPoint();
/*  560 */     } else if (dim == 1) {
/*  561 */       InteriorPointLine intPt = new InteriorPointLine(this);
/*  562 */       interiorPt = intPt.getInteriorPoint();
/*      */     } else {
/*  565 */       InteriorPointArea intPt = new InteriorPointArea(this);
/*  566 */       interiorPt = intPt.getInteriorPoint();
/*      */     } 
/*  568 */     return createPointFromInternalCoord(interiorPt, this);
/*      */   }
/*      */   
/*      */   public abstract int getDimension();
/*      */   
/*      */   public abstract Geometry getBoundary();
/*      */   
/*      */   public abstract int getBoundaryDimension();
/*      */   
/*      */   public Geometry getEnvelope() {
/*  628 */     return getFactory().toGeometry(getEnvelopeInternal());
/*      */   }
/*      */   
/*      */   public Envelope getEnvelopeInternal() {
/*  646 */     if (this.envelope == null)
/*  647 */       this.envelope = computeEnvelopeInternal(); 
/*  649 */     return new Envelope(this.envelope);
/*      */   }
/*      */   
/*      */   public void geometryChanged() {
/*  660 */     apply(geometryChangedFilter);
/*      */   }
/*      */   
/*      */   protected void geometryChangedAction() {
/*  671 */     this.envelope = null;
/*      */   }
/*      */   
/*      */   public boolean disjoint(Geometry g) {
/*  693 */     return !intersects(g);
/*      */   }
/*      */   
/*      */   public boolean touches(Geometry g) {
/*  723 */     if (!getEnvelopeInternal().intersects(g.getEnvelopeInternal()))
/*  724 */       return false; 
/*  725 */     return relate(g).isTouches(getDimension(), g.getDimension());
/*      */   }
/*      */   
/*      */   public boolean intersects(Geometry g) {
/*  754 */     if (!getEnvelopeInternal().intersects(g.getEnvelopeInternal()))
/*  755 */       return false; 
/*  774 */     if (isRectangle())
/*  775 */       return RectangleIntersects.intersects((Polygon)this, g); 
/*  777 */     if (g.isRectangle())
/*  778 */       return RectangleIntersects.intersects((Polygon)g, this); 
/*  781 */     return relate(g).isIntersects();
/*      */   }
/*      */   
/*      */   public boolean crosses(Geometry g) {
/*  810 */     if (!getEnvelopeInternal().intersects(g.getEnvelopeInternal()))
/*  811 */       return false; 
/*  812 */     return relate(g).isCrosses(getDimension(), g.getDimension());
/*      */   }
/*      */   
/*      */   public boolean within(Geometry g) {
/*  844 */     return g.contains(this);
/*      */   }
/*      */   
/*      */   public boolean contains(Geometry g) {
/*  876 */     if (!getEnvelopeInternal().contains(g.getEnvelopeInternal()))
/*  877 */       return false; 
/*  879 */     if (isRectangle())
/*  880 */       return RectangleContains.contains((Polygon)this, g); 
/*  883 */     return relate(g).isContains();
/*      */   }
/*      */   
/*      */   public boolean overlaps(Geometry g) {
/*  909 */     if (!getEnvelopeInternal().intersects(g.getEnvelopeInternal()))
/*  910 */       return false; 
/*  911 */     return relate(g).isOverlaps(getDimension(), g.getDimension());
/*      */   }
/*      */   
/*      */   public boolean covers(Geometry g) {
/*  950 */     if (!getEnvelopeInternal().covers(g.getEnvelopeInternal()))
/*  951 */       return false; 
/*  953 */     if (isRectangle())
/*  955 */       return true; 
/*  957 */     return relate(g).isCovers();
/*      */   }
/*      */   
/*      */   public boolean coveredBy(Geometry g) {
/*  990 */     return g.covers(this);
/*      */   }
/*      */   
/*      */   public boolean relate(Geometry g, String intersectionPattern) {
/* 1017 */     return relate(g).matches(intersectionPattern);
/*      */   }
/*      */   
/*      */   public IntersectionMatrix relate(Geometry g) {
/* 1028 */     checkNotGeometryCollection(this);
/* 1029 */     checkNotGeometryCollection(g);
/* 1030 */     return RelateOp.relate(this, g);
/*      */   }
/*      */   
/*      */   public boolean equals(Geometry g) {
/* 1051 */     if (g == null)
/* 1051 */       return false; 
/* 1052 */     return equalsTopo(g);
/*      */   }
/*      */   
/*      */   public boolean equalsTopo(Geometry g) {
/* 1082 */     if (!getEnvelopeInternal().equals(g.getEnvelopeInternal()))
/* 1083 */       return false; 
/* 1084 */     return relate(g).isEquals(getDimension(), g.getDimension());
/*      */   }
/*      */   
/*      */   public boolean equals(Object o) {
/* 1118 */     if (!(o instanceof Geometry))
/* 1118 */       return false; 
/* 1119 */     Geometry g = (Geometry)o;
/* 1120 */     return equalsExact(g);
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1130 */     return getEnvelopeInternal().hashCode();
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1134 */     return toText();
/*      */   }
/*      */   
/*      */   public String toText() {
/* 1145 */     WKTWriter writer = new WKTWriter();
/* 1146 */     return writer.write(this);
/*      */   }
/*      */   
/*      */   public Geometry buffer(double distance) {
/* 1176 */     return BufferOp.bufferOp(this, distance);
/*      */   }
/*      */   
/*      */   public Geometry buffer(double distance, int quadrantSegments) {
/* 1209 */     return BufferOp.bufferOp(this, distance, quadrantSegments);
/*      */   }
/*      */   
/*      */   public Geometry buffer(double distance, int quadrantSegments, int endCapStyle) {
/* 1247 */     return BufferOp.bufferOp(this, distance, quadrantSegments, endCapStyle);
/*      */   }
/*      */   
/*      */   public Geometry convexHull() {
/* 1281 */     return (new ConvexHull(this)).getConvexHull();
/*      */   }
/*      */   
/*      */   public abstract Geometry reverse();
/*      */   
/*      */   public Geometry intersection(Geometry other) {
/* 1319 */     if (isEmpty() || other.isEmpty())
/* 1320 */       return OverlayOp.createEmptyResult(1, this, other, this.factory); 
/* 1323 */     if (isGeometryCollection()) {
/* 1324 */       final Geometry g2 = other;
/* 1325 */       return GeometryCollectionMapper.map((GeometryCollection)this, new GeometryMapper.MapOp() {
/*      */             public Geometry map(Geometry g) {
/* 1329 */               return g.intersection(g2);
/*      */             }
/*      */           });
/*      */     } 
/* 1336 */     checkNotGeometryCollection(this);
/* 1337 */     checkNotGeometryCollection(other);
/* 1338 */     return SnapIfNeededOverlayOp.overlayOp(this, other, 1);
/*      */   }
/*      */   
/*      */   public Geometry union(Geometry other) {
/* 1378 */     if (isEmpty() || other.isEmpty()) {
/* 1379 */       if (isEmpty() && other.isEmpty())
/* 1380 */         return OverlayOp.createEmptyResult(2, this, other, this.factory); 
/* 1383 */       if (isEmpty())
/* 1383 */         return (Geometry)other.clone(); 
/* 1384 */       if (other.isEmpty())
/* 1384 */         return (Geometry)clone(); 
/*      */     } 
/* 1389 */     checkNotGeometryCollection(this);
/* 1390 */     checkNotGeometryCollection(other);
/* 1391 */     return SnapIfNeededOverlayOp.overlayOp(this, other, 2);
/*      */   }
/*      */   
/*      */   public Geometry difference(Geometry other) {
/* 1414 */     if (isEmpty())
/* 1414 */       return OverlayOp.createEmptyResult(3, this, other, this.factory); 
/* 1415 */     if (other.isEmpty())
/* 1415 */       return (Geometry)clone(); 
/* 1417 */     checkNotGeometryCollection(this);
/* 1418 */     checkNotGeometryCollection(other);
/* 1419 */     return SnapIfNeededOverlayOp.overlayOp(this, other, 3);
/*      */   }
/*      */   
/*      */   public Geometry symDifference(Geometry other) {
/* 1443 */     if (isEmpty() || other.isEmpty()) {
/* 1445 */       if (isEmpty() && other.isEmpty())
/* 1446 */         return OverlayOp.createEmptyResult(4, this, other, this.factory); 
/* 1449 */       if (isEmpty())
/* 1449 */         return (Geometry)other.clone(); 
/* 1450 */       if (other.isEmpty())
/* 1450 */         return (Geometry)clone(); 
/*      */     } 
/* 1453 */     checkNotGeometryCollection(this);
/* 1454 */     checkNotGeometryCollection(other);
/* 1455 */     return SnapIfNeededOverlayOp.overlayOp(this, other, 4);
/*      */   }
/*      */   
/*      */   public Geometry union() {
/* 1480 */     return UnaryUnionOp.union(this);
/*      */   }
/*      */   
/*      */   public abstract boolean equalsExact(Geometry paramGeometry, double paramDouble);
/*      */   
/*      */   public boolean equalsExact(Geometry other) {
/* 1540 */     return equalsExact(other, 0.0D);
/*      */   }
/*      */   
/*      */   public boolean equalsNorm(Geometry g) {
/* 1559 */     if (g == null)
/* 1559 */       return false; 
/* 1560 */     return norm().equalsExact(g.norm());
/*      */   }
/*      */   
/*      */   public abstract void apply(CoordinateFilter paramCoordinateFilter);
/*      */   
/*      */   public abstract void apply(CoordinateSequenceFilter paramCoordinateSequenceFilter);
/*      */   
/*      */   public abstract void apply(GeometryFilter paramGeometryFilter);
/*      */   
/*      */   public abstract void apply(GeometryComponentFilter paramGeometryComponentFilter);
/*      */   
/*      */   public Object clone() {
/*      */     try {
/* 1620 */       Geometry clone = (Geometry)super.clone();
/* 1621 */       if (clone.envelope != null)
/* 1621 */         clone.envelope = new Envelope(clone.envelope); 
/* 1622 */       return clone;
/* 1624 */     } catch (CloneNotSupportedException e) {
/* 1625 */       Assert.shouldNeverReachHere();
/* 1626 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public abstract void normalize();
/*      */   
/*      */   public Geometry norm() {
/* 1656 */     Geometry copy = (Geometry)clone();
/* 1657 */     copy.normalize();
/* 1658 */     return copy;
/*      */   }
/*      */   
/*      */   public int compareTo(Object o) {
/* 1688 */     Geometry other = (Geometry)o;
/* 1689 */     if (getClassSortIndex() != other.getClassSortIndex())
/* 1690 */       return getClassSortIndex() - other.getClassSortIndex(); 
/* 1692 */     if (isEmpty() && other.isEmpty())
/* 1693 */       return 0; 
/* 1695 */     if (isEmpty())
/* 1696 */       return -1; 
/* 1698 */     if (other.isEmpty())
/* 1699 */       return 1; 
/* 1701 */     return compareToSameClass(o);
/*      */   }
/*      */   
/*      */   public int compareTo(Object o, CoordinateSequenceComparator comp) {
/* 1735 */     Geometry other = (Geometry)o;
/* 1736 */     if (getClassSortIndex() != other.getClassSortIndex())
/* 1737 */       return getClassSortIndex() - other.getClassSortIndex(); 
/* 1739 */     if (isEmpty() && other.isEmpty())
/* 1740 */       return 0; 
/* 1742 */     if (isEmpty())
/* 1743 */       return -1; 
/* 1745 */     if (other.isEmpty())
/* 1746 */       return 1; 
/* 1748 */     return compareToSameClass(o, comp);
/*      */   }
/*      */   
/*      */   protected boolean isEquivalentClass(Geometry other) {
/* 1764 */     return getClass().getName().equals(other.getClass().getName());
/*      */   }
/*      */   
/*      */   protected void checkNotGeometryCollection(Geometry g) {
/* 1777 */     if (g.getClass().getName().equals("com.vividsolutions.jts.geom.GeometryCollection"))
/* 1778 */       throw new IllegalArgumentException("This method does not support GeometryCollection arguments"); 
/*      */   }
/*      */   
/*      */   protected boolean isGeometryCollection() {
/* 1790 */     return getClass().equals(GeometryCollection.class);
/*      */   }
/*      */   
/*      */   protected abstract Envelope computeEnvelopeInternal();
/*      */   
/*      */   protected abstract int compareToSameClass(Object paramObject);
/*      */   
/*      */   protected abstract int compareToSameClass(Object paramObject, CoordinateSequenceComparator paramCoordinateSequenceComparator);
/*      */   
/*      */   protected int compare(Collection a, Collection b) {
/* 1845 */     Iterator<Comparable> i = a.iterator();
/* 1846 */     Iterator<Comparable> j = b.iterator();
/* 1847 */     while (i.hasNext() && j.hasNext()) {
/* 1848 */       Comparable<Comparable> aElement = i.next();
/* 1849 */       Comparable bElement = j.next();
/* 1850 */       int comparison = aElement.compareTo(bElement);
/* 1851 */       if (comparison != 0)
/* 1852 */         return comparison; 
/*      */     } 
/* 1855 */     if (i.hasNext())
/* 1856 */       return 1; 
/* 1858 */     if (j.hasNext())
/* 1859 */       return -1; 
/* 1861 */     return 0;
/*      */   }
/*      */   
/*      */   protected boolean equal(Coordinate a, Coordinate b, double tolerance) {
/* 1865 */     if (tolerance == 0.0D)
/* 1865 */       return a.equals(b); 
/* 1866 */     return (a.distance(b) <= tolerance);
/*      */   }
/*      */   
/*      */   private int getClassSortIndex() {
/* 1870 */     for (int i = 0; i < sortedClasses.length; i++) {
/* 1871 */       if (sortedClasses[i].isInstance(this))
/* 1872 */         return i; 
/*      */     } 
/* 1874 */     Assert.shouldNeverReachHere("Class not supported: " + getClass());
/* 1875 */     return -1;
/*      */   }
/*      */   
/*      */   private Point createPointFromInternalCoord(Coordinate coord, Geometry exemplar) {
/* 1880 */     exemplar.getPrecisionModel().makePrecise(coord);
/* 1881 */     return exemplar.getFactory().createPoint(coord);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\Geometry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */