/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.operation.BoundaryOp;
/*     */ 
/*     */ public class LineString extends Geometry implements Lineal {
/*     */   private static final long serialVersionUID = 3110669828065365560L;
/*     */   
/*     */   protected CoordinateSequence points;
/*     */   
/*     */   public LineString(Coordinate[] points, PrecisionModel precisionModel, int SRID) {
/*  80 */     super(new GeometryFactory(precisionModel, SRID));
/*  81 */     init(getFactory().getCoordinateSequenceFactory().create(points));
/*     */   }
/*     */   
/*     */   public LineString(CoordinateSequence points, GeometryFactory factory) {
/*  92 */     super(factory);
/*  93 */     init(points);
/*     */   }
/*     */   
/*     */   private void init(CoordinateSequence points) {
/*  98 */     if (points == null)
/*  99 */       points = getFactory().getCoordinateSequenceFactory().create(new Coordinate[0]); 
/* 101 */     if (points.size() == 1)
/* 102 */       throw new IllegalArgumentException("Invalid number of points in LineString (found " + points.size() + " - must be 0 or >= 2)"); 
/* 105 */     this.points = points;
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/* 108 */     return this.points.toCoordinateArray();
/*     */   }
/*     */   
/*     */   public CoordinateSequence getCoordinateSequence() {
/* 112 */     return this.points;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinateN(int n) {
/* 116 */     return this.points.getCoordinate(n);
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/* 121 */     if (isEmpty())
/* 121 */       return null; 
/* 122 */     return this.points.getCoordinate(0);
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 126 */     return 1;
/*     */   }
/*     */   
/*     */   public int getBoundaryDimension() {
/* 130 */     if (isClosed())
/* 131 */       return -1; 
/* 133 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 137 */     return (this.points.size() == 0);
/*     */   }
/*     */   
/*     */   public int getNumPoints() {
/* 141 */     return this.points.size();
/*     */   }
/*     */   
/*     */   public Point getPointN(int n) {
/* 145 */     return getFactory().createPoint(this.points.getCoordinate(n));
/*     */   }
/*     */   
/*     */   public Point getStartPoint() {
/* 149 */     if (isEmpty())
/* 150 */       return null; 
/* 152 */     return getPointN(0);
/*     */   }
/*     */   
/*     */   public Point getEndPoint() {
/* 156 */     if (isEmpty())
/* 157 */       return null; 
/* 159 */     return getPointN(getNumPoints() - 1);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 163 */     if (isEmpty())
/* 164 */       return false; 
/* 166 */     return getCoordinateN(0).equals2D(getCoordinateN(getNumPoints() - 1));
/*     */   }
/*     */   
/*     */   public boolean isRing() {
/* 170 */     return (isClosed() && isSimple());
/*     */   }
/*     */   
/*     */   public String getGeometryType() {
/* 174 */     return "LineString";
/*     */   }
/*     */   
/*     */   public double getLength() {
/* 184 */     return CGAlgorithms.length(this.points);
/*     */   }
/*     */   
/*     */   public Geometry getBoundary() {
/* 195 */     return (new BoundaryOp(this)).getBoundary();
/*     */   }
/*     */   
/*     */   public Geometry reverse() {
/* 206 */     CoordinateSequence seq = (CoordinateSequence)this.points.clone();
/* 207 */     CoordinateSequences.reverse(seq);
/* 208 */     LineString revLine = getFactory().createLineString(seq);
/* 209 */     return revLine;
/*     */   }
/*     */   
/*     */   public boolean isCoordinate(Coordinate pt) {
/* 220 */     for (int i = 0; i < this.points.size(); i++) {
/* 221 */       if (this.points.getCoordinate(i).equals(pt))
/* 222 */         return true; 
/*     */     } 
/* 225 */     return false;
/*     */   }
/*     */   
/*     */   protected Envelope computeEnvelopeInternal() {
/* 229 */     if (isEmpty())
/* 230 */       return new Envelope(); 
/* 232 */     return this.points.expandEnvelope(new Envelope());
/*     */   }
/*     */   
/*     */   public boolean equalsExact(Geometry other, double tolerance) {
/* 236 */     if (!isEquivalentClass(other))
/* 237 */       return false; 
/* 239 */     LineString otherLineString = (LineString)other;
/* 240 */     if (this.points.size() != otherLineString.points.size())
/* 241 */       return false; 
/* 243 */     for (int i = 0; i < this.points.size(); i++) {
/* 244 */       if (!equal(this.points.getCoordinate(i), otherLineString.points.getCoordinate(i), tolerance))
/* 245 */         return false; 
/*     */     } 
/* 248 */     return true;
/*     */   }
/*     */   
/*     */   public void apply(CoordinateFilter filter) {
/* 252 */     for (int i = 0; i < this.points.size(); i++)
/* 253 */       filter.filter(this.points.getCoordinate(i)); 
/*     */   }
/*     */   
/*     */   public void apply(CoordinateSequenceFilter filter) {
/* 259 */     if (this.points.size() == 0)
/*     */       return; 
/* 261 */     for (int i = 0; i < this.points.size(); i++) {
/* 262 */       filter.filter(this.points, i);
/* 263 */       if (filter.isDone())
/*     */         break; 
/*     */     } 
/* 266 */     if (filter.isGeometryChanged())
/* 267 */       geometryChanged(); 
/*     */   }
/*     */   
/*     */   public void apply(GeometryFilter filter) {
/* 271 */     filter.filter(this);
/*     */   }
/*     */   
/*     */   public void apply(GeometryComponentFilter filter) {
/* 275 */     filter.filter(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 285 */     LineString ls = (LineString)super.clone();
/* 286 */     ls.points = (CoordinateSequence)this.points.clone();
/* 287 */     return ls;
/*     */   }
/*     */   
/*     */   public void normalize() {
/* 297 */     for (int i = 0; i < this.points.size() / 2; i++) {
/* 298 */       int j = this.points.size() - 1 - i;
/* 300 */       if (!this.points.getCoordinate(i).equals(this.points.getCoordinate(j))) {
/* 301 */         if (this.points.getCoordinate(i).compareTo(this.points.getCoordinate(j)) > 0)
/* 302 */           CoordinateArrays.reverse(getCoordinates()); 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isEquivalentClass(Geometry other) {
/* 310 */     return other instanceof LineString;
/*     */   }
/*     */   
/*     */   protected int compareToSameClass(Object o) {
/* 315 */     LineString line = (LineString)o;
/* 317 */     int i = 0;
/* 318 */     int j = 0;
/* 319 */     while (i < this.points.size() && j < line.points.size()) {
/* 320 */       int comparison = this.points.getCoordinate(i).compareTo(line.points.getCoordinate(j));
/* 321 */       if (comparison != 0)
/* 322 */         return comparison; 
/* 324 */       i++;
/* 325 */       j++;
/*     */     } 
/* 327 */     if (i < this.points.size())
/* 328 */       return 1; 
/* 330 */     if (j < line.points.size())
/* 331 */       return -1; 
/* 333 */     return 0;
/*     */   }
/*     */   
/*     */   protected int compareToSameClass(Object o, CoordinateSequenceComparator comp) {
/* 338 */     LineString line = (LineString)o;
/* 339 */     return comp.compare(this.points, line.points);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\LineString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */