/*     */ package com.vividsolutions.jts.geom.impl;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequences;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.lang.ref.SoftReference;
/*     */ 
/*     */ public abstract class PackedCoordinateSequence implements CoordinateSequence {
/*     */   protected int dimension;
/*     */   
/*     */   protected SoftReference coordRef;
/*     */   
/*     */   public int getDimension() {
/*  70 */     return this.dimension;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/*  77 */     Coordinate[] coords = getCachedCoords();
/*  78 */     if (coords != null)
/*  79 */       return coords[i]; 
/*  81 */     return getCoordinateInternal(i);
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinateCopy(int i) {
/*  87 */     return getCoordinateInternal(i);
/*     */   }
/*     */   
/*     */   public void getCoordinate(int i, Coordinate coord) {
/*  94 */     coord.x = getOrdinate(i, 0);
/*  95 */     coord.y = getOrdinate(i, 1);
/*  96 */     if (this.dimension > 2)
/*  96 */       coord.z = getOrdinate(i, 2); 
/*     */   }
/*     */   
/*     */   public Coordinate[] toCoordinateArray() {
/* 103 */     Coordinate[] coords = getCachedCoords();
/* 105 */     if (coords != null)
/* 106 */       return coords; 
/* 108 */     coords = new Coordinate[size()];
/* 109 */     for (int i = 0; i < coords.length; i++)
/* 110 */       coords[i] = getCoordinateInternal(i); 
/* 112 */     this.coordRef = new SoftReference<>(coords);
/* 114 */     return coords;
/*     */   }
/*     */   
/*     */   private Coordinate[] getCachedCoords() {
/* 121 */     if (this.coordRef != null) {
/* 122 */       Coordinate[] coords = this.coordRef.get();
/* 123 */       if (coords != null)
/* 124 */         return coords; 
/* 127 */       this.coordRef = null;
/* 128 */       return null;
/*     */     } 
/* 132 */     return null;
/*     */   }
/*     */   
/*     */   public double getX(int index) {
/* 141 */     return getOrdinate(index, 0);
/*     */   }
/*     */   
/*     */   public double getY(int index) {
/* 148 */     return getOrdinate(index, 1);
/*     */   }
/*     */   
/*     */   public abstract double getOrdinate(int paramInt1, int paramInt2);
/*     */   
/*     */   public void setX(int index, double value) {
/* 163 */     this.coordRef = null;
/* 164 */     setOrdinate(index, 0, value);
/*     */   }
/*     */   
/*     */   public void setY(int index, double value) {
/* 174 */     this.coordRef = null;
/* 175 */     setOrdinate(index, 1, value);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 180 */     return CoordinateSequences.toString(this);
/*     */   }
/*     */   
/*     */   protected abstract Coordinate getCoordinateInternal(int paramInt);
/*     */   
/*     */   public abstract Object clone();
/*     */   
/*     */   public abstract void setOrdinate(int paramInt1, int paramInt2, double paramDouble);
/*     */   
/*     */   public static class Double extends PackedCoordinateSequence {
/*     */     double[] coords;
/*     */     
/*     */     public Double(double[] coords, int dimensions) {
/* 230 */       if (dimensions < 2)
/* 231 */         throw new IllegalArgumentException("Must have at least 2 dimensions"); 
/* 233 */       if (coords.length % dimensions != 0)
/* 234 */         throw new IllegalArgumentException("Packed array does not contain an integral number of coordinates"); 
/* 237 */       this.dimension = dimensions;
/* 238 */       this.coords = coords;
/*     */     }
/*     */     
/*     */     public Double(float[] coordinates, int dimensions) {
/* 247 */       this.coords = new double[coordinates.length];
/* 248 */       this.dimension = dimensions;
/* 249 */       for (int i = 0; i < coordinates.length; i++)
/* 250 */         this.coords[i] = coordinates[i]; 
/*     */     }
/*     */     
/*     */     public Double(Coordinate[] coordinates, int dimension) {
/* 260 */       if (coordinates == null)
/* 261 */         coordinates = new Coordinate[0]; 
/* 262 */       this.dimension = dimension;
/* 264 */       this.coords = new double[coordinates.length * this.dimension];
/* 265 */       for (int i = 0; i < coordinates.length; i++) {
/* 266 */         this.coords[i * this.dimension] = (coordinates[i]).x;
/* 267 */         if (this.dimension >= 2)
/* 268 */           this.coords[i * this.dimension + 1] = (coordinates[i]).y; 
/* 269 */         if (this.dimension >= 3)
/* 270 */           this.coords[i * this.dimension + 2] = (coordinates[i]).z; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public Double(Coordinate[] coordinates) {
/* 279 */       this(coordinates, 3);
/*     */     }
/*     */     
/*     */     public Double(int size, int dimension) {
/* 288 */       this.dimension = dimension;
/* 289 */       this.coords = new double[size * this.dimension];
/*     */     }
/*     */     
/*     */     public Coordinate getCoordinateInternal(int i) {
/* 296 */       double x = this.coords[i * this.dimension];
/* 297 */       double y = this.coords[i * this.dimension + 1];
/* 298 */       double z = (this.dimension == 2) ? Double.NaN : this.coords[i * this.dimension + 2];
/* 299 */       return new Coordinate(x, y, z);
/*     */     }
/*     */     
/*     */     public double[] getRawCoordinates() {
/* 309 */       return this.coords;
/*     */     }
/*     */     
/*     */     public int size() {
/* 316 */       return this.coords.length / this.dimension;
/*     */     }
/*     */     
/*     */     public Object clone() {
/* 323 */       double[] clone = new double[this.coords.length];
/* 324 */       System.arraycopy(this.coords, 0, clone, 0, this.coords.length);
/* 325 */       return new Double(clone, this.dimension);
/*     */     }
/*     */     
/*     */     public double getOrdinate(int index, int ordinate) {
/* 335 */       return this.coords[index * this.dimension + ordinate];
/*     */     }
/*     */     
/*     */     public void setOrdinate(int index, int ordinate, double value) {
/* 343 */       this.coordRef = null;
/* 344 */       this.coords[index * this.dimension + ordinate] = value;
/*     */     }
/*     */     
/*     */     public Envelope expandEnvelope(Envelope env) {
/* 349 */       for (int i = 0; i < this.coords.length; i += this.dimension)
/* 350 */         env.expandToInclude(this.coords[i], this.coords[i + 1]); 
/* 352 */       return env;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Float extends PackedCoordinateSequence {
/*     */     float[] coords;
/*     */     
/*     */     public Float(float[] coords, int dimensions) {
/* 373 */       if (dimensions < 2)
/* 374 */         throw new IllegalArgumentException("Must have at least 2 dimensions"); 
/* 376 */       if (coords.length % dimensions != 0)
/* 377 */         throw new IllegalArgumentException("Packed array does not contain an integral number of coordinates"); 
/* 380 */       this.dimension = dimensions;
/* 381 */       this.coords = coords;
/*     */     }
/*     */     
/*     */     public Float(double[] coordinates, int dimensions) {
/* 391 */       this.coords = new float[coordinates.length];
/* 392 */       this.dimension = dimensions;
/* 393 */       for (int i = 0; i < coordinates.length; i++)
/* 394 */         this.coords[i] = (float)coordinates[i]; 
/*     */     }
/*     */     
/*     */     public Float(Coordinate[] coordinates, int dimension) {
/* 404 */       if (coordinates == null)
/* 405 */         coordinates = new Coordinate[0]; 
/* 406 */       this.dimension = dimension;
/* 408 */       this.coords = new float[coordinates.length * this.dimension];
/* 409 */       for (int i = 0; i < coordinates.length; i++) {
/* 410 */         this.coords[i * this.dimension] = (float)(coordinates[i]).x;
/* 411 */         if (this.dimension >= 2)
/* 412 */           this.coords[i * this.dimension + 1] = (float)(coordinates[i]).y; 
/* 413 */         if (this.dimension >= 3)
/* 414 */           this.coords[i * this.dimension + 2] = (float)(coordinates[i]).z; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public Float(int size, int dimension) {
/* 424 */       this.dimension = dimension;
/* 425 */       this.coords = new float[size * this.dimension];
/*     */     }
/*     */     
/*     */     public Coordinate getCoordinateInternal(int i) {
/* 432 */       double x = this.coords[i * this.dimension];
/* 433 */       double y = this.coords[i * this.dimension + 1];
/* 434 */       double z = (this.dimension == 2) ? Double.NaN : this.coords[i * this.dimension + 2];
/* 435 */       return new Coordinate(x, y, z);
/*     */     }
/*     */     
/*     */     public float[] getRawCoordinates() {
/* 445 */       return this.coords;
/*     */     }
/*     */     
/*     */     public int size() {
/* 452 */       return this.coords.length / this.dimension;
/*     */     }
/*     */     
/*     */     public Object clone() {
/* 459 */       float[] clone = new float[this.coords.length];
/* 460 */       System.arraycopy(this.coords, 0, clone, 0, this.coords.length);
/* 461 */       return new Float(clone, this.dimension);
/*     */     }
/*     */     
/*     */     public double getOrdinate(int index, int ordinate) {
/* 471 */       return this.coords[index * this.dimension + ordinate];
/*     */     }
/*     */     
/*     */     public void setOrdinate(int index, int ordinate, double value) {
/* 479 */       this.coordRef = null;
/* 480 */       this.coords[index * this.dimension + ordinate] = (float)value;
/*     */     }
/*     */     
/*     */     public Envelope expandEnvelope(Envelope env) {
/* 485 */       for (int i = 0; i < this.coords.length; i += this.dimension)
/* 486 */         env.expandToInclude(this.coords[i], this.coords[i + 1]); 
/* 488 */       return env;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\impl\PackedCoordinateSequence.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */