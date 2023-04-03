/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class Polygon extends Geometry implements Polygonal {
/*     */   private static final long serialVersionUID = -3494792200821764533L;
/*     */   
/*  78 */   protected LinearRing shell = null;
/*     */   
/*     */   protected LinearRing[] holes;
/*     */   
/*     */   public Polygon(LinearRing shell, PrecisionModel precisionModel, int SRID) {
/* 100 */     this(shell, new LinearRing[0], new GeometryFactory(precisionModel, SRID));
/*     */   }
/*     */   
/*     */   public Polygon(LinearRing shell, LinearRing[] holes, PrecisionModel precisionModel, int SRID) {
/* 120 */     this(shell, holes, new GeometryFactory(precisionModel, SRID));
/*     */   }
/*     */   
/*     */   public Polygon(LinearRing shell, LinearRing[] holes, GeometryFactory factory) {
/* 135 */     super(factory);
/* 136 */     if (shell == null)
/* 137 */       shell = getFactory().createLinearRing((CoordinateSequence)null); 
/* 139 */     if (holes == null)
/* 140 */       holes = new LinearRing[0]; 
/* 142 */     if (hasNullElements((Object[])holes))
/* 143 */       throw new IllegalArgumentException("holes must not contain null elements"); 
/* 145 */     if (shell.isEmpty() && hasNonEmptyElements((Geometry[])holes))
/* 146 */       throw new IllegalArgumentException("shell is empty but holes are not"); 
/* 148 */     this.shell = shell;
/* 149 */     this.holes = holes;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/* 153 */     return this.shell.getCoordinate();
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/* 157 */     if (isEmpty())
/* 158 */       return new Coordinate[0]; 
/* 160 */     Coordinate[] coordinates = new Coordinate[getNumPoints()];
/* 161 */     int k = -1;
/* 162 */     Coordinate[] shellCoordinates = this.shell.getCoordinates();
/* 163 */     for (int x = 0; x < shellCoordinates.length; x++) {
/* 164 */       k++;
/* 165 */       coordinates[k] = shellCoordinates[x];
/*     */     } 
/* 167 */     for (int i = 0; i < this.holes.length; i++) {
/* 168 */       Coordinate[] childCoordinates = this.holes[i].getCoordinates();
/* 169 */       for (int j = 0; j < childCoordinates.length; j++) {
/* 170 */         k++;
/* 171 */         coordinates[k] = childCoordinates[j];
/*     */       } 
/*     */     } 
/* 174 */     return coordinates;
/*     */   }
/*     */   
/*     */   public int getNumPoints() {
/* 178 */     int numPoints = this.shell.getNumPoints();
/* 179 */     for (int i = 0; i < this.holes.length; i++)
/* 180 */       numPoints += this.holes[i].getNumPoints(); 
/* 182 */     return numPoints;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 186 */     return 2;
/*     */   }
/*     */   
/*     */   public int getBoundaryDimension() {
/* 190 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 194 */     return this.shell.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean isRectangle() {
/* 211 */     if (getNumInteriorRing() != 0)
/* 211 */       return false; 
/* 212 */     if (this.shell == null)
/* 212 */       return false; 
/* 213 */     if (this.shell.getNumPoints() != 5)
/* 213 */       return false; 
/* 215 */     CoordinateSequence seq = this.shell.getCoordinateSequence();
/* 218 */     Envelope env = getEnvelopeInternal();
/* 219 */     for (int i = 0; i < 5; i++) {
/* 220 */       double x = seq.getX(i);
/* 221 */       if (x != env.getMinX() && x != env.getMaxX())
/* 221 */         return false; 
/* 222 */       double y = seq.getY(i);
/* 223 */       if (y != env.getMinY() && y != env.getMaxY())
/* 223 */         return false; 
/*     */     } 
/* 227 */     double prevX = seq.getX(0);
/* 228 */     double prevY = seq.getY(0);
/* 229 */     for (int j = 1; j <= 4; j++) {
/* 230 */       double x = seq.getX(j);
/* 231 */       double y = seq.getY(j);
/* 232 */       boolean xChanged = (x != prevX);
/* 233 */       boolean yChanged = (y != prevY);
/* 234 */       if (xChanged == yChanged)
/* 235 */         return false; 
/* 236 */       prevX = x;
/* 237 */       prevY = y;
/*     */     } 
/* 239 */     return true;
/*     */   }
/*     */   
/*     */   public LineString getExteriorRing() {
/* 243 */     return this.shell;
/*     */   }
/*     */   
/*     */   public int getNumInteriorRing() {
/* 247 */     return this.holes.length;
/*     */   }
/*     */   
/*     */   public LineString getInteriorRingN(int n) {
/* 251 */     return this.holes[n];
/*     */   }
/*     */   
/*     */   public String getGeometryType() {
/* 255 */     return "Polygon";
/*     */   }
/*     */   
/*     */   public double getArea() {
/* 265 */     double area = 0.0D;
/* 266 */     area += Math.abs(CGAlgorithms.signedArea(this.shell.getCoordinateSequence()));
/* 267 */     for (int i = 0; i < this.holes.length; i++)
/* 268 */       area -= Math.abs(CGAlgorithms.signedArea(this.holes[i].getCoordinateSequence())); 
/* 270 */     return area;
/*     */   }
/*     */   
/*     */   public double getLength() {
/* 280 */     double len = 0.0D;
/* 281 */     len += this.shell.getLength();
/* 282 */     for (int i = 0; i < this.holes.length; i++)
/* 283 */       len += this.holes[i].getLength(); 
/* 285 */     return len;
/*     */   }
/*     */   
/*     */   public Geometry getBoundary() {
/* 295 */     if (isEmpty())
/* 296 */       return getFactory().createMultiLineString(null); 
/* 298 */     LinearRing[] rings = new LinearRing[this.holes.length + 1];
/* 299 */     rings[0] = this.shell;
/* 300 */     for (int i = 0; i < this.holes.length; i++)
/* 301 */       rings[i + 1] = this.holes[i]; 
/* 304 */     if (rings.length <= 1)
/* 305 */       return getFactory().createLinearRing(rings[0].getCoordinateSequence()); 
/* 306 */     return getFactory().createMultiLineString((LineString[])rings);
/*     */   }
/*     */   
/*     */   protected Envelope computeEnvelopeInternal() {
/* 310 */     return this.shell.getEnvelopeInternal();
/*     */   }
/*     */   
/*     */   public boolean equalsExact(Geometry other, double tolerance) {
/* 314 */     if (!isEquivalentClass(other))
/* 315 */       return false; 
/* 317 */     Polygon otherPolygon = (Polygon)other;
/* 318 */     Geometry thisShell = this.shell;
/* 319 */     Geometry otherPolygonShell = otherPolygon.shell;
/* 320 */     if (!thisShell.equalsExact(otherPolygonShell, tolerance))
/* 321 */       return false; 
/* 323 */     if (this.holes.length != otherPolygon.holes.length)
/* 324 */       return false; 
/* 326 */     for (int i = 0; i < this.holes.length; i++) {
/* 327 */       if (!this.holes[i].equalsExact(otherPolygon.holes[i], tolerance))
/* 328 */         return false; 
/*     */     } 
/* 331 */     return true;
/*     */   }
/*     */   
/*     */   public void apply(CoordinateFilter filter) {
/* 335 */     this.shell.apply(filter);
/* 336 */     for (int i = 0; i < this.holes.length; i++)
/* 337 */       this.holes[i].apply(filter); 
/*     */   }
/*     */   
/*     */   public void apply(CoordinateSequenceFilter filter) {
/* 343 */     this.shell.apply(filter);
/* 344 */     if (!filter.isDone())
/* 345 */       for (int i = 0; i < this.holes.length; i++) {
/* 346 */         this.holes[i].apply(filter);
/* 347 */         if (filter.isDone())
/*     */           break; 
/*     */       }  
/* 351 */     if (filter.isGeometryChanged())
/* 352 */       geometryChanged(); 
/*     */   }
/*     */   
/*     */   public void apply(GeometryFilter filter) {
/* 356 */     filter.filter(this);
/*     */   }
/*     */   
/*     */   public void apply(GeometryComponentFilter filter) {
/* 360 */     filter.filter(this);
/* 361 */     this.shell.apply(filter);
/* 362 */     for (int i = 0; i < this.holes.length; i++)
/* 363 */       this.holes[i].apply(filter); 
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 374 */     Polygon poly = (Polygon)super.clone();
/* 375 */     poly.shell = (LinearRing)this.shell.clone();
/* 376 */     poly.holes = new LinearRing[this.holes.length];
/* 377 */     for (int i = 0; i < this.holes.length; i++)
/* 378 */       poly.holes[i] = (LinearRing)this.holes[i].clone(); 
/* 380 */     return poly;
/*     */   }
/*     */   
/*     */   public Geometry convexHull() {
/* 384 */     return getExteriorRing().convexHull();
/*     */   }
/*     */   
/*     */   public void normalize() {
/* 388 */     normalize(this.shell, true);
/* 389 */     for (int i = 0; i < this.holes.length; i++)
/* 390 */       normalize(this.holes[i], false); 
/* 392 */     Arrays.sort((Object[])this.holes);
/*     */   }
/*     */   
/*     */   protected int compareToSameClass(Object o) {
/* 396 */     LinearRing thisShell = this.shell;
/* 397 */     LinearRing otherShell = ((Polygon)o).shell;
/* 398 */     return thisShell.compareToSameClass(otherShell);
/*     */   }
/*     */   
/*     */   protected int compareToSameClass(Object o, CoordinateSequenceComparator comp) {
/* 402 */     Polygon poly = (Polygon)o;
/* 404 */     LinearRing thisShell = this.shell;
/* 405 */     LinearRing otherShell = poly.shell;
/* 406 */     int shellComp = thisShell.compareToSameClass(otherShell, comp);
/* 407 */     if (shellComp != 0)
/* 407 */       return shellComp; 
/* 409 */     int nHole1 = getNumInteriorRing();
/* 410 */     int nHole2 = poly.getNumInteriorRing();
/* 411 */     int i = 0;
/* 412 */     while (i < nHole1 && i < nHole2) {
/* 413 */       LinearRing thisHole = (LinearRing)getInteriorRingN(i);
/* 414 */       LinearRing otherHole = (LinearRing)poly.getInteriorRingN(i);
/* 415 */       int holeComp = thisHole.compareToSameClass(otherHole, comp);
/* 416 */       if (holeComp != 0)
/* 416 */         return holeComp; 
/* 417 */       i++;
/*     */     } 
/* 419 */     if (i < nHole1)
/* 419 */       return 1; 
/* 420 */     if (i < nHole2)
/* 420 */       return -1; 
/* 421 */     return 0;
/*     */   }
/*     */   
/*     */   private void normalize(LinearRing ring, boolean clockwise) {
/* 425 */     if (ring.isEmpty())
/*     */       return; 
/* 428 */     Coordinate[] uniqueCoordinates = new Coordinate[(ring.getCoordinates()).length - 1];
/* 429 */     System.arraycopy(ring.getCoordinates(), 0, uniqueCoordinates, 0, uniqueCoordinates.length);
/* 430 */     Coordinate minCoordinate = CoordinateArrays.minCoordinate(ring.getCoordinates());
/* 431 */     CoordinateArrays.scroll(uniqueCoordinates, minCoordinate);
/* 432 */     System.arraycopy(uniqueCoordinates, 0, ring.getCoordinates(), 0, uniqueCoordinates.length);
/* 433 */     ring.getCoordinates()[uniqueCoordinates.length] = uniqueCoordinates[0];
/* 434 */     if (CGAlgorithms.isCCW(ring.getCoordinates()) == clockwise)
/* 435 */       CoordinateArrays.reverse(ring.getCoordinates()); 
/*     */   }
/*     */   
/*     */   public Geometry reverse() {
/* 441 */     Polygon poly = (Polygon)super.clone();
/* 442 */     poly.shell = (LinearRing)((LinearRing)this.shell.clone()).reverse();
/* 443 */     poly.holes = new LinearRing[this.holes.length];
/* 444 */     for (int i = 0; i < this.holes.length; i++)
/* 445 */       poly.holes[i] = (LinearRing)((LinearRing)this.holes[i].clone()).reverse(); 
/* 447 */     return poly;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\Polygon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */