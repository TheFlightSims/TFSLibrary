/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.impl.PackedCoordinateSequence;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class LiteCoordinateSequence extends PackedCoordinateSequence {
/*  44 */   private static final GeometryFactory geomFac = new GeometryFactory(new LiteCoordinateSequenceFactory());
/*     */   
/*     */   private double[] coords;
/*     */   
/*     */   private int size;
/*     */   
/*     */   public LiteCoordinateSequence(double[] coords, int dimensions) {
/*  64 */     init(coords, dimensions);
/*     */   }
/*     */   
/*     */   void init(double[] coords, int dimensions) {
/*  73 */     this.dimension = dimensions;
/*  74 */     if (dimensions < 2)
/*  75 */       throw new IllegalArgumentException("Invalid dimensions, must be at least 2"); 
/*  76 */     if (coords.length % this.dimension != 0)
/*  77 */       throw new IllegalArgumentException("Packed array does not contain an integral number of coordinates"); 
/*  80 */     this.coords = coords;
/*  81 */     this.size = coords.length / this.dimension;
/*     */   }
/*     */   
/*     */   public LiteCoordinateSequence(double[] coords) {
/*  91 */     init(coords, 2);
/*     */   }
/*     */   
/*     */   public LiteCoordinateSequence(float[] coordinates, int dimension) {
/* 100 */     double[] dcoords = new double[coordinates.length];
/* 101 */     for (int i = 0; i < coordinates.length; i++)
/* 102 */       dcoords[i] = coordinates[i]; 
/* 104 */     init(dcoords, dimension);
/*     */   }
/*     */   
/*     */   public LiteCoordinateSequence(float[] coordinates) {
/* 113 */     this(coordinates, 2);
/*     */   }
/*     */   
/*     */   public LiteCoordinateSequence(Coordinate[] coordinates) {
/* 122 */     if (coordinates == null)
/* 123 */       coordinates = new Coordinate[0]; 
/* 124 */     this.dimension = guessDimension(coordinates);
/* 126 */     this.coords = new double[coordinates.length * this.dimension];
/* 127 */     for (int i = 0; i < coordinates.length; i++) {
/* 128 */       this.coords[i * this.dimension] = (coordinates[i]).x;
/* 129 */       if (this.dimension > 2) {
/* 130 */         this.coords[i * this.dimension + 1] = (coordinates[i]).y;
/* 131 */         this.coords[i * this.dimension + 2] = (coordinates[i]).z;
/* 132 */       } else if (this.dimension > 1) {
/* 133 */         this.coords[i * this.dimension + 1] = (coordinates[i]).y;
/*     */       } 
/*     */     } 
/* 136 */     this.size = coordinates.length;
/*     */   }
/*     */   
/*     */   private int guessDimension(Coordinate[] coordinates) {
/* 140 */     for (Coordinate c : coordinates) {
/* 141 */       if (!Double.isNaN(c.z))
/* 142 */         return 3; 
/*     */     } 
/* 146 */     return 2;
/*     */   }
/*     */   
/*     */   public LiteCoordinateSequence(int size, int dimension) {
/* 157 */     this.dimension = dimension;
/* 158 */     this.coords = new double[size * this.dimension];
/* 159 */     this.size = this.coords.length / dimension;
/*     */   }
/*     */   
/*     */   public LiteCoordinateSequence(LiteCoordinateSequence seq) {
/* 170 */     this.dimension = seq.dimension;
/* 171 */     this.size = seq.size;
/* 172 */     double[] orig = seq.getArray();
/* 173 */     this.coords = new double[orig.length];
/* 174 */     System.arraycopy(orig, 0, this.coords, 0, this.coords.length);
/*     */   }
/*     */   
/*     */   public LiteCoordinateSequence(CoordinateSequence cs, int dimension) {
/* 179 */     this.size = cs.size();
/* 180 */     this.dimension = dimension;
/* 182 */     if (cs instanceof LiteCoordinateSequence) {
/* 183 */       double[] orig = ((LiteCoordinateSequence)cs).getOrdinateArray(dimension);
/* 184 */       this.coords = new double[orig.length];
/* 185 */       System.arraycopy(orig, 0, this.coords, 0, this.coords.length);
/*     */     } else {
/* 187 */       this.coords = new double[this.size * dimension];
/* 188 */       int minDimension = Math.min(dimension, cs.getDimension());
/* 189 */       for (int i = 0; i < this.size; i++) {
/* 190 */         for (int j = 0; j < minDimension; j++)
/* 191 */           this.coords[i * dimension + j] = cs.getOrdinate(i, j); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinateInternal(int i) {
/* 202 */     double x = this.coords[i * this.dimension];
/* 203 */     double y = this.coords[i * this.dimension + 1];
/* 204 */     double z = (this.dimension == 2) ? Double.NaN : this.coords[i * this.dimension + 2];
/* 205 */     return new Coordinate(x, y, z);
/*     */   }
/*     */   
/*     */   public int size() {
/* 212 */     return this.size;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 219 */     double[] clone = new double[this.coords.length];
/* 220 */     System.arraycopy(this.coords, 0, clone, 0, this.coords.length);
/* 221 */     return new LiteCoordinateSequence(clone, this.dimension);
/*     */   }
/*     */   
/*     */   public double getOrdinate(int index, int ordinate) {
/* 231 */     return this.coords[index * this.dimension + ordinate];
/*     */   }
/*     */   
/*     */   public double getX(int index) {
/* 238 */     return this.coords[index * this.dimension];
/*     */   }
/*     */   
/*     */   public double getY(int index) {
/* 245 */     return this.coords[index * this.dimension + 1];
/*     */   }
/*     */   
/*     */   public void setOrdinate(int index, int ordinate, double value) {
/* 253 */     this.coordRef = null;
/* 254 */     this.coords[index * this.dimension + ordinate] = value;
/*     */   }
/*     */   
/*     */   public Envelope expandEnvelope(Envelope env) {
/* 259 */     double minx = this.coords[0];
/* 260 */     double maxx = minx;
/* 261 */     double miny = this.coords[1];
/* 262 */     double maxy = miny;
/*     */     int i;
/* 263 */     for (i = 0; i < this.coords.length; i += this.dimension) {
/* 264 */       double x = this.coords[i];
/* 265 */       if (x < minx) {
/* 266 */         minx = x;
/* 267 */       } else if (x > maxx) {
/* 268 */         maxx = x;
/*     */       } 
/* 269 */       double y = this.coords[i + 1];
/* 270 */       if (y < miny) {
/* 271 */         miny = y;
/* 272 */       } else if (y > maxy) {
/* 273 */         maxy = y;
/*     */       } 
/*     */     } 
/* 275 */     env.expandToInclude(minx, miny);
/* 276 */     env.expandToInclude(maxx, maxy);
/* 277 */     return env;
/*     */   }
/*     */   
/*     */   public double[] getArray() {
/* 283 */     return this.coords;
/*     */   }
/*     */   
/*     */   public void setArray(double[] coords2) {
/* 290 */     this.coords = coords2;
/* 291 */     this.size = this.coords.length / this.dimension;
/* 292 */     this.coordRef = null;
/*     */   }
/*     */   
/*     */   public void setArray(double[] coords2, int dimension) {
/* 296 */     this.coords = coords2;
/* 297 */     this.dimension = dimension;
/* 298 */     this.size = this.coords.length / dimension;
/* 299 */     this.coordRef = null;
/*     */   }
/*     */   
/*     */   public double[] getXYArray() {
/* 308 */     if (this.dimension == 2)
/* 309 */       return this.coords; 
/* 312 */     int n = size();
/* 313 */     double[] result = new double[n * 2];
/* 314 */     for (int t = 0; t < n; t++) {
/* 316 */       result[t * 2] = getOrdinate(t, 0);
/* 317 */       result[t * 2 + 1] = getOrdinate(t, 1);
/*     */     } 
/* 319 */     return result;
/*     */   }
/*     */   
/*     */   public double[] getOrdinateArray(int dimensions) {
/* 323 */     if (dimensions == this.dimension)
/* 324 */       return this.coords; 
/* 327 */     int n = size();
/* 328 */     double[] result = new double[n * dimensions];
/* 329 */     int minDimensions = Math.min(dimensions, this.dimension);
/* 330 */     for (int t = 0; t < n; t++) {
/* 331 */       for (int d = 0; d < minDimensions; d++)
/* 332 */         result[t * 2 + d] = getOrdinate(t, d); 
/*     */     } 
/* 335 */     return result;
/*     */   }
/*     */   
/*     */   public static Geometry cloneGeometry(Geometry geom, int dimension) {
/* 347 */     if (dimension < 2)
/* 348 */       throw new IllegalArgumentException("Invalid dimension value, must be >= 2"); 
/* 351 */     if (geom == null)
/* 352 */       return null; 
/* 354 */     if (geom instanceof LineString)
/* 355 */       return cloneGeometry((LineString)geom, dimension); 
/* 356 */     if (geom instanceof Polygon)
/* 357 */       return cloneGeometry((Polygon)geom, dimension); 
/* 358 */     if (geom instanceof Point)
/* 359 */       return cloneGeometry((Point)geom, dimension); 
/* 361 */     return cloneGeometry((GeometryCollection)geom, dimension);
/*     */   }
/*     */   
/*     */   public static final Geometry cloneGeometry(Geometry geom) {
/* 371 */     return cloneGeometry(geom, 2);
/*     */   }
/*     */   
/*     */   private static final Geometry cloneGeometry(Polygon geom, int dimension) {
/* 379 */     LinearRing lr = (LinearRing)cloneGeometry((LinearRing)geom.getExteriorRing(), dimension);
/* 380 */     LinearRing[] rings = new LinearRing[geom.getNumInteriorRing()];
/* 381 */     for (int t = 0; t < rings.length; t++)
/* 382 */       rings[t] = (LinearRing)cloneGeometry((LinearRing)geom.getInteriorRingN(t), dimension); 
/* 384 */     return (Geometry)geomFac.createPolygon(lr, rings);
/*     */   }
/*     */   
/*     */   private static final Geometry cloneGeometry(Point geom, int dimension) {
/* 388 */     return (Geometry)geomFac.createPoint((CoordinateSequence)new LiteCoordinateSequence(geom.getCoordinateSequence(), dimension));
/*     */   }
/*     */   
/*     */   private static final Geometry cloneGeometry(LineString geom, int dimension) {
/* 392 */     return (Geometry)geomFac.createLineString((CoordinateSequence)new LiteCoordinateSequence(geom.getCoordinateSequence(), dimension));
/*     */   }
/*     */   
/*     */   private static final Geometry cloneGeometry(LinearRing geom, int dimension) {
/* 396 */     return (Geometry)geomFac.createLinearRing((CoordinateSequence)new LiteCoordinateSequence(geom.getCoordinateSequence(), dimension));
/*     */   }
/*     */   
/*     */   private static final Geometry cloneGeometry(GeometryCollection geom, int dimension) {
/* 400 */     if (geom.getNumGeometries() == 0) {
/* 401 */       Geometry[] arrayOfGeometry = new Geometry[0];
/* 402 */       return (Geometry)geomFac.createGeometryCollection(arrayOfGeometry);
/*     */     } 
/* 405 */     ArrayList<Geometry> gs = new ArrayList(geom.getNumGeometries());
/* 406 */     int n = geom.getNumGeometries();
/* 407 */     for (int t = 0; t < n; t++)
/* 408 */       gs.add(cloneGeometry(geom.getGeometryN(t), dimension)); 
/* 410 */     return geomFac.buildGeometry(gs);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 415 */     if (this.size > 0) {
/* 416 */       StringBuffer strBuf = new StringBuffer(9 * this.dimension * this.size);
/* 417 */       strBuf.append('(');
/* 418 */       for (int i = 0; i < this.size; i++) {
/* 419 */         for (int j = 0; j < this.dimension; j++) {
/* 420 */           strBuf.append(this.coords[i * this.dimension + j]);
/* 421 */           if (j < this.dimension - 1)
/* 422 */             strBuf.append(" "); 
/*     */         } 
/* 425 */         if (i < this.size - 1)
/* 426 */           strBuf.append(", "); 
/*     */       } 
/* 429 */       strBuf.append(')');
/* 430 */       return strBuf.toString();
/*     */     } 
/* 432 */     return "()";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\LiteCoordinateSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */