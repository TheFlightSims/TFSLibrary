/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public class Point extends Geometry implements Puntal {
/*     */   private static final long serialVersionUID = 4902022702746614570L;
/*     */   
/*     */   private CoordinateSequence coordinates;
/*     */   
/*     */   public Point(Coordinate coordinate, PrecisionModel precisionModel, int SRID) {
/*  73 */     super(new GeometryFactory(precisionModel, SRID));
/*  74 */     (new Coordinate[1])[0] = coordinate;
/*  74 */     init(getFactory().getCoordinateSequenceFactory().create((coordinate != null) ? new Coordinate[1] : new Coordinate[0]));
/*     */   }
/*     */   
/*     */   public Point(CoordinateSequence coordinates, GeometryFactory factory) {
/*  83 */     super(factory);
/*  84 */     init(coordinates);
/*     */   }
/*     */   
/*     */   private void init(CoordinateSequence coordinates) {
/*  89 */     if (coordinates == null)
/*  90 */       coordinates = getFactory().getCoordinateSequenceFactory().create(new Coordinate[0]); 
/*  92 */     Assert.isTrue((coordinates.size() <= 1));
/*  93 */     this.coordinates = coordinates;
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/*  97 */     (new Coordinate[1])[0] = getCoordinate();
/*  97 */     return isEmpty() ? new Coordinate[0] : new Coordinate[1];
/*     */   }
/*     */   
/*     */   public int getNumPoints() {
/* 103 */     return isEmpty() ? 0 : 1;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 107 */     return (getCoordinate() == null);
/*     */   }
/*     */   
/*     */   public boolean isSimple() {
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 115 */     return 0;
/*     */   }
/*     */   
/*     */   public int getBoundaryDimension() {
/* 119 */     return -1;
/*     */   }
/*     */   
/*     */   public double getX() {
/* 123 */     if (getCoordinate() == null)
/* 124 */       throw new IllegalStateException("getX called on empty Point"); 
/* 126 */     return (getCoordinate()).x;
/*     */   }
/*     */   
/*     */   public double getY() {
/* 130 */     if (getCoordinate() == null)
/* 131 */       throw new IllegalStateException("getY called on empty Point"); 
/* 133 */     return (getCoordinate()).y;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/* 137 */     return (this.coordinates.size() != 0) ? this.coordinates.getCoordinate(0) : null;
/*     */   }
/*     */   
/*     */   public String getGeometryType() {
/* 141 */     return "Point";
/*     */   }
/*     */   
/*     */   public Geometry getBoundary() {
/* 153 */     return getFactory().createGeometryCollection(null);
/*     */   }
/*     */   
/*     */   protected Envelope computeEnvelopeInternal() {
/* 157 */     if (isEmpty())
/* 158 */       return new Envelope(); 
/* 160 */     Envelope env = new Envelope();
/* 161 */     env.expandToInclude(this.coordinates.getX(0), this.coordinates.getY(0));
/* 162 */     return env;
/*     */   }
/*     */   
/*     */   public boolean equalsExact(Geometry other, double tolerance) {
/* 166 */     if (!isEquivalentClass(other))
/* 167 */       return false; 
/* 169 */     if (isEmpty() && other.isEmpty())
/* 170 */       return true; 
/* 172 */     if (isEmpty() != other.isEmpty())
/* 173 */       return false; 
/* 175 */     return equal(((Point)other).getCoordinate(), getCoordinate(), tolerance);
/*     */   }
/*     */   
/*     */   public void apply(CoordinateFilter filter) {
/* 179 */     if (isEmpty())
/*     */       return; 
/* 180 */     filter.filter(getCoordinate());
/*     */   }
/*     */   
/*     */   public void apply(CoordinateSequenceFilter filter) {
/* 185 */     if (isEmpty())
/*     */       return; 
/* 187 */     filter.filter(this.coordinates, 0);
/* 188 */     if (filter.isGeometryChanged())
/* 189 */       geometryChanged(); 
/*     */   }
/*     */   
/*     */   public void apply(GeometryFilter filter) {
/* 193 */     filter.filter(this);
/*     */   }
/*     */   
/*     */   public void apply(GeometryComponentFilter filter) {
/* 197 */     filter.filter(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 207 */     Point p = (Point)super.clone();
/* 208 */     p.coordinates = (CoordinateSequence)this.coordinates.clone();
/* 209 */     return p;
/*     */   }
/*     */   
/*     */   public Geometry reverse() {
/* 214 */     return (Geometry)clone();
/*     */   }
/*     */   
/*     */   public void normalize() {}
/*     */   
/*     */   protected int compareToSameClass(Object other) {
/* 223 */     Point point = (Point)other;
/* 224 */     return getCoordinate().compareTo(point.getCoordinate());
/*     */   }
/*     */   
/*     */   protected int compareToSameClass(Object other, CoordinateSequenceComparator comp) {
/* 229 */     Point point = (Point)other;
/* 230 */     return comp.compare(this.coordinates, point.coordinates);
/*     */   }
/*     */   
/*     */   public CoordinateSequence getCoordinateSequence() {
/* 234 */     return this.coordinates;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\Point.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */