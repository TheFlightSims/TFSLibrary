/*     */ package org.geotools.geometry.jts.coordinatesequence;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.impl.PackedCoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.impl.PackedCoordinateSequenceFactory;
/*     */ 
/*     */ public abstract class PackedCSBuilder implements CSBuilder {
/*  34 */   int size = -1;
/*     */   
/*  36 */   int dimensions = -1;
/*     */   
/*     */   public int getSize() {
/*  42 */     return this.size;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/*  49 */     return this.dimensions;
/*     */   }
/*     */   
/*     */   public static class Double extends PackedCSBuilder {
/*     */     double[] ordinates;
/*     */     
/*  55 */     PackedCoordinateSequenceFactory factory = new PackedCoordinateSequenceFactory(0);
/*     */     
/*     */     public void start(int size, int dimensions) {
/*  62 */       this.ordinates = new double[size * dimensions];
/*  63 */       this.size = size;
/*  64 */       this.dimensions = dimensions;
/*     */     }
/*     */     
/*     */     public CoordinateSequence end() {
/*  71 */       CoordinateSequence cs = this.factory.create(this.ordinates, this.dimensions);
/*  72 */       this.ordinates = null;
/*  73 */       this.size = -1;
/*  74 */       this.dimensions = -1;
/*  75 */       return cs;
/*     */     }
/*     */     
/*     */     public void setOrdinate(double value, int ordinateIndex, int coordinateIndex) {
/*  84 */       this.ordinates[coordinateIndex * this.dimensions + ordinateIndex] = value;
/*     */     }
/*     */     
/*     */     public double getOrdinate(int ordinateIndex, int coordinateIndex) {
/*  92 */       return this.ordinates[coordinateIndex * this.dimensions + ordinateIndex];
/*     */     }
/*     */     
/*     */     public void setOrdinate(CoordinateSequence sequence, double value, int ordinateIndex, int coordinateIndex) {
/*  99 */       PackedCoordinateSequence pcs = (PackedCoordinateSequence)sequence;
/* 100 */       pcs.setOrdinate(coordinateIndex, ordinateIndex, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Float extends PackedCSBuilder {
/*     */     float[] ordinates;
/*     */     
/* 108 */     PackedCoordinateSequenceFactory factory = new PackedCoordinateSequenceFactory(1);
/*     */     
/*     */     public void start(int size, int dimensions) {
/* 115 */       this.ordinates = new float[size * dimensions];
/* 116 */       this.size = size;
/* 117 */       this.dimensions = dimensions;
/*     */     }
/*     */     
/*     */     public CoordinateSequence end() {
/* 124 */       CoordinateSequence cs = this.factory.create(this.ordinates, this.dimensions);
/* 125 */       this.ordinates = null;
/* 126 */       this.size = -1;
/* 127 */       this.dimensions = -1;
/* 128 */       return cs;
/*     */     }
/*     */     
/*     */     public void setOrdinate(double value, int ordinateIndex, int coordinateIndex) {
/* 137 */       this.ordinates[coordinateIndex * this.dimensions + ordinateIndex] = (float)value;
/*     */     }
/*     */     
/*     */     public void setOrdinate(CoordinateSequence sequence, double value, int ordinateIndex, int coordinateIndex) {
/* 144 */       PackedCoordinateSequence pcs = (PackedCoordinateSequence)sequence;
/* 145 */       pcs.setOrdinate(coordinateIndex, ordinateIndex, value);
/*     */     }
/*     */     
/*     */     public double getOrdinate(int ordinateIndex, int coordinateIndex) {
/* 153 */       return this.ordinates[coordinateIndex * this.dimensions + ordinateIndex];
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\coordinatesequence\PackedCSBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */