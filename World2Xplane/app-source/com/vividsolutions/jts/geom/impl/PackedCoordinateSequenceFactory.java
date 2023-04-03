/*     */ package com.vividsolutions.jts.geom.impl;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ 
/*     */ public class PackedCoordinateSequenceFactory implements CoordinateSequenceFactory {
/*     */   public static final int DOUBLE = 0;
/*     */   
/*     */   public static final int FLOAT = 1;
/*     */   
/*  47 */   public static final PackedCoordinateSequenceFactory DOUBLE_FACTORY = new PackedCoordinateSequenceFactory(0);
/*     */   
/*  49 */   public static final PackedCoordinateSequenceFactory FLOAT_FACTORY = new PackedCoordinateSequenceFactory(1);
/*     */   
/*  52 */   private int type = 0;
/*     */   
/*  53 */   private int dimension = 3;
/*     */   
/*     */   public PackedCoordinateSequenceFactory() {
/*  61 */     this(0);
/*     */   }
/*     */   
/*     */   public PackedCoordinateSequenceFactory(int type) {
/*  73 */     this(type, 3);
/*     */   }
/*     */   
/*     */   public PackedCoordinateSequenceFactory(int type, int dimension) {
/*  84 */     setType(type);
/*  85 */     setDimension(dimension);
/*     */   }
/*     */   
/*     */   public int getType() {
/*  94 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(int type) {
/* 103 */     if (type != 0 && type != 1)
/* 104 */       throw new IllegalArgumentException("Unknown type " + type); 
/* 105 */     this.type = type;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 109 */     return this.dimension;
/*     */   }
/*     */   
/*     */   public void setDimension(int dimension) {
/* 111 */     this.dimension = dimension;
/*     */   }
/*     */   
/*     */   public CoordinateSequence create(Coordinate[] coordinates) {
/* 117 */     if (this.type == 0)
/* 118 */       return new PackedCoordinateSequence.Double(coordinates, this.dimension); 
/* 120 */     return new PackedCoordinateSequence.Float(coordinates, this.dimension);
/*     */   }
/*     */   
/*     */   public CoordinateSequence create(CoordinateSequence coordSeq) {
/* 128 */     if (this.type == 0)
/* 129 */       return new PackedCoordinateSequence.Double(coordSeq.toCoordinateArray(), this.dimension); 
/* 131 */     return new PackedCoordinateSequence.Float(coordSeq.toCoordinateArray(), this.dimension);
/*     */   }
/*     */   
/*     */   public CoordinateSequence create(double[] packedCoordinates, int dimension) {
/* 140 */     if (this.type == 0)
/* 141 */       return new PackedCoordinateSequence.Double(packedCoordinates, dimension); 
/* 143 */     return new PackedCoordinateSequence.Float(packedCoordinates, dimension);
/*     */   }
/*     */   
/*     */   public CoordinateSequence create(float[] packedCoordinates, int dimension) {
/* 152 */     if (this.type == 0)
/* 153 */       return new PackedCoordinateSequence.Double(packedCoordinates, dimension); 
/* 155 */     return new PackedCoordinateSequence.Float(packedCoordinates, dimension);
/*     */   }
/*     */   
/*     */   public CoordinateSequence create(int size, int dimension) {
/* 163 */     if (this.type == 0)
/* 164 */       return new PackedCoordinateSequence.Double(size, dimension); 
/* 166 */     return new PackedCoordinateSequence.Float(size, dimension);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\impl\PackedCoordinateSequenceFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */