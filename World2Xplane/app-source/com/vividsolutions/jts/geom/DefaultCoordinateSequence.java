/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ class DefaultCoordinateSequence implements CoordinateSequence, Serializable {
/*     */   private static final long serialVersionUID = -915438501601840650L;
/*     */   
/*     */   private Coordinate[] coordinates;
/*     */   
/*     */   public DefaultCoordinateSequence(Coordinate[] coordinates) {
/*  60 */     if (Geometry.hasNullElements((Object[])coordinates))
/*  61 */       throw new IllegalArgumentException("Null coordinate"); 
/*  63 */     this.coordinates = coordinates;
/*     */   }
/*     */   
/*     */   public DefaultCoordinateSequence(CoordinateSequence coordSeq) {
/*  72 */     this.coordinates = new Coordinate[coordSeq.size()];
/*  73 */     for (int i = 0; i < this.coordinates.length; i++)
/*  74 */       this.coordinates[i] = coordSeq.getCoordinateCopy(i); 
/*     */   }
/*     */   
/*     */   public DefaultCoordinateSequence(int size) {
/*  85 */     this.coordinates = new Coordinate[size];
/*  86 */     for (int i = 0; i < size; i++)
/*  87 */       this.coordinates[i] = new Coordinate(); 
/*     */   }
/*     */   
/*     */   public int getDimension() {
/*  94 */     return 3;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/* 104 */     return this.coordinates[i];
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinateCopy(int i) {
/* 113 */     return new Coordinate(this.coordinates[i]);
/*     */   }
/*     */   
/*     */   public void getCoordinate(int index, Coordinate coord) {
/* 120 */     coord.x = (this.coordinates[index]).x;
/* 121 */     coord.y = (this.coordinates[index]).y;
/*     */   }
/*     */   
/*     */   public double getX(int index) {
/* 128 */     return (this.coordinates[index]).x;
/*     */   }
/*     */   
/*     */   public double getY(int index) {
/* 135 */     return (this.coordinates[index]).y;
/*     */   }
/*     */   
/*     */   public double getOrdinate(int index, int ordinateIndex) {
/* 143 */     switch (ordinateIndex) {
/*     */       case 0:
/* 144 */         return (this.coordinates[index]).x;
/*     */       case 1:
/* 145 */         return (this.coordinates[index]).y;
/*     */       case 2:
/* 146 */         return (this.coordinates[index]).z;
/*     */     } 
/* 148 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public void setOrdinate(int index, int ordinateIndex, double value) {
/* 155 */     switch (ordinateIndex) {
/*     */       case 0:
/* 156 */         (this.coordinates[index]).x = value;
/*     */         break;
/*     */       case 1:
/* 157 */         (this.coordinates[index]).y = value;
/*     */         break;
/*     */       case 2:
/* 158 */         (this.coordinates[index]).z = value;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 167 */     Coordinate[] cloneCoordinates = new Coordinate[size()];
/* 168 */     for (int i = 0; i < this.coordinates.length; i++)
/* 169 */       cloneCoordinates[i] = (Coordinate)this.coordinates[i].clone(); 
/* 171 */     return new DefaultCoordinateSequence(cloneCoordinates);
/*     */   }
/*     */   
/*     */   public int size() {
/* 179 */     return this.coordinates.length;
/*     */   }
/*     */   
/*     */   public Coordinate[] toCoordinateArray() {
/* 187 */     return this.coordinates;
/*     */   }
/*     */   
/*     */   public Envelope expandEnvelope(Envelope env) {
/* 192 */     for (int i = 0; i < this.coordinates.length; i++)
/* 193 */       env.expandToInclude(this.coordinates[i]); 
/* 195 */     return env;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 204 */     if (this.coordinates.length > 0) {
/* 205 */       StringBuffer strBuf = new StringBuffer(17 * this.coordinates.length);
/* 206 */       strBuf.append('(');
/* 207 */       strBuf.append(this.coordinates[0]);
/* 208 */       for (int i = 1; i < this.coordinates.length; i++) {
/* 209 */         strBuf.append(", ");
/* 210 */         strBuf.append(this.coordinates[i]);
/*     */       } 
/* 212 */       strBuf.append(')');
/* 213 */       return strBuf.toString();
/*     */     } 
/* 215 */     return "()";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\DefaultCoordinateSequence.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */