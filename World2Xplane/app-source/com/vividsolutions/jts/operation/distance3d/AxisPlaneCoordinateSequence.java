/*     */ package com.vividsolutions.jts.operation.distance3d;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ 
/*     */ public class AxisPlaneCoordinateSequence implements CoordinateSequence {
/*     */   public static CoordinateSequence projectToXY(CoordinateSequence seq) {
/*  65 */     return new AxisPlaneCoordinateSequence(seq, XY_INDEX);
/*     */   }
/*     */   
/*     */   public static CoordinateSequence projectToXZ(CoordinateSequence seq) {
/*  76 */     return new AxisPlaneCoordinateSequence(seq, XZ_INDEX);
/*     */   }
/*     */   
/*     */   public static CoordinateSequence projectToYZ(CoordinateSequence seq) {
/*  87 */     return new AxisPlaneCoordinateSequence(seq, YZ_INDEX);
/*     */   }
/*     */   
/*  90 */   private static final int[] XY_INDEX = new int[] { 0, 1 };
/*     */   
/*  91 */   private static final int[] XZ_INDEX = new int[] { 0, 2 };
/*     */   
/*  92 */   private static final int[] YZ_INDEX = new int[] { 1, 2 };
/*     */   
/*     */   private CoordinateSequence seq;
/*     */   
/*     */   private int[] indexMap;
/*     */   
/*     */   private AxisPlaneCoordinateSequence(CoordinateSequence seq, int[] indexMap) {
/*  98 */     this.seq = seq;
/*  99 */     this.indexMap = indexMap;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 103 */     return 2;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/* 107 */     return getCoordinateCopy(i);
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinateCopy(int i) {
/* 111 */     return new Coordinate(getX(i), getY(i), getZ(i));
/*     */   }
/*     */   
/*     */   public void getCoordinate(int index, Coordinate coord) {
/* 115 */     coord.x = getOrdinate(index, 0);
/* 116 */     coord.y = getOrdinate(index, 1);
/* 117 */     coord.z = getOrdinate(index, 2);
/*     */   }
/*     */   
/*     */   public double getX(int index) {
/* 121 */     return getOrdinate(index, 0);
/*     */   }
/*     */   
/*     */   public double getY(int index) {
/* 125 */     return getOrdinate(index, 1);
/*     */   }
/*     */   
/*     */   public double getZ(int index) {
/* 129 */     return getOrdinate(index, 2);
/*     */   }
/*     */   
/*     */   public double getOrdinate(int index, int ordinateIndex) {
/* 134 */     if (ordinateIndex > 1)
/* 134 */       return 0.0D; 
/* 135 */     return this.seq.getOrdinate(index, this.indexMap[ordinateIndex]);
/*     */   }
/*     */   
/*     */   public int size() {
/* 139 */     return this.seq.size();
/*     */   }
/*     */   
/*     */   public void setOrdinate(int index, int ordinateIndex, double value) {
/* 143 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Coordinate[] toCoordinateArray() {
/* 147 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Envelope expandEnvelope(Envelope env) {
/* 151 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 156 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\distance3d\AxisPlaneCoordinateSequence.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */