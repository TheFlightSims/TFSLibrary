/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.Arrays;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class GeometryCollection extends Geometry {
/*     */   private static final long serialVersionUID = -5694727726395021467L;
/*     */   
/*     */   protected Geometry[] geometries;
/*     */   
/*     */   public GeometryCollection(Geometry[] geometries, PrecisionModel precisionModel, int SRID) {
/*  59 */     this(geometries, new GeometryFactory(precisionModel, SRID));
/*     */   }
/*     */   
/*     */   public GeometryCollection(Geometry[] geometries, GeometryFactory factory) {
/*  71 */     super(factory);
/*  72 */     if (geometries == null)
/*  73 */       geometries = new Geometry[0]; 
/*  75 */     if (hasNullElements((Object[])geometries))
/*  76 */       throw new IllegalArgumentException("geometries must not contain null elements"); 
/*  78 */     this.geometries = geometries;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  82 */     if (isEmpty())
/*  82 */       return null; 
/*  83 */     return this.geometries[0].getCoordinate();
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/*  96 */     Coordinate[] coordinates = new Coordinate[getNumPoints()];
/*  97 */     int k = -1;
/*  98 */     for (int i = 0; i < this.geometries.length; i++) {
/*  99 */       Coordinate[] childCoordinates = this.geometries[i].getCoordinates();
/* 100 */       for (int j = 0; j < childCoordinates.length; j++) {
/* 101 */         k++;
/* 102 */         coordinates[k] = childCoordinates[j];
/*     */       } 
/*     */     } 
/* 105 */     return coordinates;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 109 */     for (int i = 0; i < this.geometries.length; i++) {
/* 110 */       if (!this.geometries[i].isEmpty())
/* 111 */         return false; 
/*     */     } 
/* 114 */     return true;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 118 */     int dimension = -1;
/* 119 */     for (int i = 0; i < this.geometries.length; i++)
/* 120 */       dimension = Math.max(dimension, this.geometries[i].getDimension()); 
/* 122 */     return dimension;
/*     */   }
/*     */   
/*     */   public int getBoundaryDimension() {
/* 126 */     int dimension = -1;
/* 127 */     for (int i = 0; i < this.geometries.length; i++)
/* 128 */       dimension = Math.max(dimension, this.geometries[i].getBoundaryDimension()); 
/* 130 */     return dimension;
/*     */   }
/*     */   
/*     */   public int getNumGeometries() {
/* 134 */     return this.geometries.length;
/*     */   }
/*     */   
/*     */   public Geometry getGeometryN(int n) {
/* 138 */     return this.geometries[n];
/*     */   }
/*     */   
/*     */   public int getNumPoints() {
/* 142 */     int numPoints = 0;
/* 143 */     for (int i = 0; i < this.geometries.length; i++)
/* 144 */       numPoints += this.geometries[i].getNumPoints(); 
/* 146 */     return numPoints;
/*     */   }
/*     */   
/*     */   public String getGeometryType() {
/* 150 */     return "GeometryCollection";
/*     */   }
/*     */   
/*     */   public Geometry getBoundary() {
/* 154 */     checkNotGeometryCollection(this);
/* 155 */     Assert.shouldNeverReachHere();
/* 156 */     return null;
/*     */   }
/*     */   
/*     */   public double getArea() {
/* 166 */     double area = 0.0D;
/* 167 */     for (int i = 0; i < this.geometries.length; i++)
/* 168 */       area += this.geometries[i].getArea(); 
/* 170 */     return area;
/*     */   }
/*     */   
/*     */   public double getLength() {
/* 175 */     double sum = 0.0D;
/* 176 */     for (int i = 0; i < this.geometries.length; i++)
/* 177 */       sum += this.geometries[i].getLength(); 
/* 179 */     return sum;
/*     */   }
/*     */   
/*     */   public boolean equalsExact(Geometry other, double tolerance) {
/* 183 */     if (!isEquivalentClass(other))
/* 184 */       return false; 
/* 186 */     GeometryCollection otherCollection = (GeometryCollection)other;
/* 187 */     if (this.geometries.length != otherCollection.geometries.length)
/* 188 */       return false; 
/* 190 */     for (int i = 0; i < this.geometries.length; i++) {
/* 191 */       if (!this.geometries[i].equalsExact(otherCollection.geometries[i], tolerance))
/* 192 */         return false; 
/*     */     } 
/* 195 */     return true;
/*     */   }
/*     */   
/*     */   public void apply(CoordinateFilter filter) {
/* 199 */     for (int i = 0; i < this.geometries.length; i++)
/* 200 */       this.geometries[i].apply(filter); 
/*     */   }
/*     */   
/*     */   public void apply(CoordinateSequenceFilter filter) {
/* 205 */     if (this.geometries.length == 0)
/*     */       return; 
/* 207 */     for (int i = 0; i < this.geometries.length; i++) {
/* 208 */       this.geometries[i].apply(filter);
/* 209 */       if (filter.isDone())
/*     */         break; 
/*     */     } 
/* 213 */     if (filter.isGeometryChanged())
/* 214 */       geometryChanged(); 
/*     */   }
/*     */   
/*     */   public void apply(GeometryFilter filter) {
/* 218 */     filter.filter(this);
/* 219 */     for (int i = 0; i < this.geometries.length; i++)
/* 220 */       this.geometries[i].apply(filter); 
/*     */   }
/*     */   
/*     */   public void apply(GeometryComponentFilter filter) {
/* 225 */     filter.filter(this);
/* 226 */     for (int i = 0; i < this.geometries.length; i++)
/* 227 */       this.geometries[i].apply(filter); 
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 238 */     GeometryCollection gc = (GeometryCollection)super.clone();
/* 239 */     gc.geometries = new Geometry[this.geometries.length];
/* 240 */     for (int i = 0; i < this.geometries.length; i++)
/* 241 */       gc.geometries[i] = (Geometry)this.geometries[i].clone(); 
/* 243 */     return gc;
/*     */   }
/*     */   
/*     */   public void normalize() {
/* 247 */     for (int i = 0; i < this.geometries.length; i++)
/* 248 */       this.geometries[i].normalize(); 
/* 250 */     Arrays.sort((Object[])this.geometries);
/*     */   }
/*     */   
/*     */   protected Envelope computeEnvelopeInternal() {
/* 254 */     Envelope envelope = new Envelope();
/* 255 */     for (int i = 0; i < this.geometries.length; i++)
/* 256 */       envelope.expandToInclude(this.geometries[i].getEnvelopeInternal()); 
/* 258 */     return envelope;
/*     */   }
/*     */   
/*     */   protected int compareToSameClass(Object o) {
/* 262 */     TreeSet theseElements = new TreeSet(Arrays.asList((Object[])this.geometries));
/* 263 */     TreeSet otherElements = new TreeSet(Arrays.asList((Object[])((GeometryCollection)o).geometries));
/* 264 */     return compare(theseElements, otherElements);
/*     */   }
/*     */   
/*     */   protected int compareToSameClass(Object o, CoordinateSequenceComparator comp) {
/* 268 */     GeometryCollection gc = (GeometryCollection)o;
/* 270 */     int n1 = getNumGeometries();
/* 271 */     int n2 = gc.getNumGeometries();
/* 272 */     int i = 0;
/* 273 */     while (i < n1 && i < n2) {
/* 274 */       Geometry thisGeom = getGeometryN(i);
/* 275 */       Geometry otherGeom = gc.getGeometryN(i);
/* 276 */       int holeComp = thisGeom.compareToSameClass(otherGeom, comp);
/* 277 */       if (holeComp != 0)
/* 277 */         return holeComp; 
/* 278 */       i++;
/*     */     } 
/* 280 */     if (i < n1)
/* 280 */       return 1; 
/* 281 */     if (i < n2)
/* 281 */       return -1; 
/* 282 */     return 0;
/*     */   }
/*     */   
/*     */   public Geometry reverse() {
/* 295 */     int n = this.geometries.length;
/* 296 */     Geometry[] revGeoms = new Geometry[n];
/* 297 */     for (int i = 0; i < this.geometries.length; i++)
/* 298 */       revGeoms[i] = this.geometries[i].reverse(); 
/* 300 */     return getFactory().createGeometryCollection(revGeoms);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\GeometryCollection.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */