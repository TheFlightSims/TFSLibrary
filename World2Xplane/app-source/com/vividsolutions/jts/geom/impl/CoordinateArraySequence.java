/*     */ package com.vividsolutions.jts.geom.impl;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class CoordinateArraySequence implements CoordinateSequence, Serializable {
/*     */   private static final long serialVersionUID = -915438501601840650L;
/*     */   
/*  61 */   private int dimension = 3;
/*     */   
/*     */   private Coordinate[] coordinates;
/*     */   
/*     */   public CoordinateArraySequence(Coordinate[] coordinates) {
/*  74 */     this(coordinates, 3);
/*     */   }
/*     */   
/*     */   public CoordinateArraySequence(Coordinate[] coordinates, int dimension) {
/*  86 */     this.coordinates = coordinates;
/*  87 */     this.dimension = dimension;
/*  88 */     if (coordinates == null)
/*  89 */       this.coordinates = new Coordinate[0]; 
/*     */   }
/*     */   
/*     */   public CoordinateArraySequence(int size) {
/*  99 */     this.coordinates = new Coordinate[size];
/* 100 */     for (int i = 0; i < size; i++)
/* 101 */       this.coordinates[i] = new Coordinate(); 
/*     */   }
/*     */   
/*     */   public CoordinateArraySequence(int size, int dimension) {
/* 113 */     this.coordinates = new Coordinate[size];
/* 114 */     this.dimension = dimension;
/* 115 */     for (int i = 0; i < size; i++)
/* 116 */       this.coordinates[i] = new Coordinate(); 
/*     */   }
/*     */   
/*     */   public CoordinateArraySequence(CoordinateSequence coordSeq) {
/* 129 */     if (coordSeq == null) {
/* 130 */       this.coordinates = new Coordinate[0];
/*     */       return;
/*     */     } 
/* 133 */     this.dimension = coordSeq.getDimension();
/* 134 */     this.coordinates = new Coordinate[coordSeq.size()];
/* 136 */     for (int i = 0; i < this.coordinates.length; i++)
/* 137 */       this.coordinates[i] = coordSeq.getCoordinateCopy(i); 
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 144 */     return this.dimension;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/* 154 */     return this.coordinates[i];
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinateCopy(int i) {
/* 164 */     return new Coordinate(this.coordinates[i]);
/*     */   }
/*     */   
/*     */   public void getCoordinate(int index, Coordinate coord) {
/* 171 */     coord.x = (this.coordinates[index]).x;
/* 172 */     coord.y = (this.coordinates[index]).y;
/* 173 */     coord.z = (this.coordinates[index]).z;
/*     */   }
/*     */   
/*     */   public double getX(int index) {
/* 180 */     return (this.coordinates[index]).x;
/*     */   }
/*     */   
/*     */   public double getY(int index) {
/* 187 */     return (this.coordinates[index]).y;
/*     */   }
/*     */   
/*     */   public double getOrdinate(int index, int ordinateIndex) {
/* 195 */     switch (ordinateIndex) {
/*     */       case 0:
/* 196 */         return (this.coordinates[index]).x;
/*     */       case 1:
/* 197 */         return (this.coordinates[index]).y;
/*     */       case 2:
/* 198 */         return (this.coordinates[index]).z;
/*     */     } 
/* 200 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 209 */     Coordinate[] cloneCoordinates = new Coordinate[size()];
/* 210 */     for (int i = 0; i < this.coordinates.length; i++)
/* 211 */       cloneCoordinates[i] = (Coordinate)this.coordinates[i].clone(); 
/* 213 */     return new CoordinateArraySequence(cloneCoordinates);
/*     */   }
/*     */   
/*     */   public int size() {
/* 221 */     return this.coordinates.length;
/*     */   }
/*     */   
/*     */   public void setOrdinate(int index, int ordinateIndex, double value) {
/* 229 */     switch (ordinateIndex) {
/*     */       case 0:
/* 231 */         (this.coordinates[index]).x = value;
/*     */         return;
/*     */       case 1:
/* 234 */         (this.coordinates[index]).y = value;
/*     */         return;
/*     */       case 2:
/* 237 */         (this.coordinates[index]).z = value;
/*     */         return;
/*     */     } 
/* 240 */     throw new IllegalArgumentException("invalid ordinateIndex");
/*     */   }
/*     */   
/*     */   public Coordinate[] toCoordinateArray() {
/* 250 */     return this.coordinates;
/*     */   }
/*     */   
/*     */   public Envelope expandEnvelope(Envelope env) {
/* 255 */     for (int i = 0; i < this.coordinates.length; i++)
/* 256 */       env.expandToInclude(this.coordinates[i]); 
/* 258 */     return env;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 267 */     if (this.coordinates.length > 0) {
/* 268 */       StringBuffer strBuf = new StringBuffer(17 * this.coordinates.length);
/* 269 */       strBuf.append('(');
/* 270 */       strBuf.append(this.coordinates[0]);
/* 271 */       for (int i = 1; i < this.coordinates.length; i++) {
/* 272 */         strBuf.append(", ");
/* 273 */         strBuf.append(this.coordinates[i]);
/*     */       } 
/* 275 */       strBuf.append(')');
/* 276 */       return strBuf.toString();
/*     */     } 
/* 278 */     return "()";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\impl\CoordinateArraySequence.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */