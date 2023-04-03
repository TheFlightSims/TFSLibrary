/*     */ package com.vividsolutions.jtsexample.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ 
/*     */ public class ExtendedCoordinateSequence implements CoordinateSequence {
/*     */   private ExtendedCoordinate[] coordinates;
/*     */   
/*     */   public static ExtendedCoordinate[] copy(Coordinate[] coordinates) {
/*  52 */     ExtendedCoordinate[] copy = new ExtendedCoordinate[coordinates.length];
/*  53 */     for (int i = 0; i < coordinates.length; i++)
/*  54 */       copy[i] = new ExtendedCoordinate(coordinates[i]); 
/*  56 */     return copy;
/*     */   }
/*     */   
/*     */   public static ExtendedCoordinate[] copy(CoordinateSequence coordSeq) {
/*  61 */     ExtendedCoordinate[] copy = new ExtendedCoordinate[coordSeq.size()];
/*  62 */     for (int i = 0; i < coordSeq.size(); i++)
/*  63 */       copy[i] = new ExtendedCoordinate(coordSeq.getCoordinate(i)); 
/*  65 */     return copy;
/*     */   }
/*     */   
/*     */   public ExtendedCoordinateSequence(ExtendedCoordinate[] coordinates) {
/*  74 */     this.coordinates = coordinates;
/*     */   }
/*     */   
/*     */   public ExtendedCoordinateSequence(Coordinate[] copyCoords) {
/*  83 */     this.coordinates = copy(copyCoords);
/*     */   }
/*     */   
/*     */   public ExtendedCoordinateSequence(CoordinateSequence coordSeq) {
/*  90 */     this.coordinates = copy(coordSeq);
/*     */   }
/*     */   
/*     */   public ExtendedCoordinateSequence(int size) {
/* 100 */     this.coordinates = new ExtendedCoordinate[size];
/* 101 */     for (int i = 0; i < size; i++)
/* 102 */       this.coordinates[i] = new ExtendedCoordinate(); 
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 109 */     return 4;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/* 112 */     return this.coordinates[i];
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinateCopy(int index) {
/* 119 */     return new Coordinate(this.coordinates[index]);
/*     */   }
/*     */   
/*     */   public void getCoordinate(int index, Coordinate coord) {
/* 125 */     coord.x = (this.coordinates[index]).x;
/* 126 */     coord.y = (this.coordinates[index]).y;
/*     */   }
/*     */   
/*     */   public double getX(int index) {
/* 134 */     return (this.coordinates[index]).x;
/*     */   }
/*     */   
/*     */   public double getY(int index) {
/* 141 */     return (this.coordinates[index]).y;
/*     */   }
/*     */   
/*     */   public double getOrdinate(int index, int ordinateIndex) {
/* 149 */     switch (ordinateIndex) {
/*     */       case 0:
/* 150 */         return (this.coordinates[index]).x;
/*     */       case 1:
/* 151 */         return (this.coordinates[index]).y;
/*     */       case 2:
/* 152 */         return (this.coordinates[index]).z;
/*     */       case 3:
/* 153 */         return this.coordinates[index].getM();
/*     */     } 
/* 155 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public void setOrdinate(int index, int ordinateIndex, double value) {
/* 163 */     switch (ordinateIndex) {
/*     */       case 0:
/* 165 */         (this.coordinates[index]).x = value;
/*     */         break;
/*     */       case 1:
/* 168 */         (this.coordinates[index]).y = value;
/*     */         break;
/*     */       case 2:
/* 171 */         (this.coordinates[index]).z = value;
/*     */         break;
/*     */       case 3:
/* 174 */         this.coordinates[index].setM(value);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 180 */     ExtendedCoordinate[] cloneCoordinates = new ExtendedCoordinate[size()];
/* 181 */     for (int i = 0; i < this.coordinates.length; i++)
/* 182 */       cloneCoordinates[i] = (ExtendedCoordinate)this.coordinates[i].clone(); 
/* 185 */     return new ExtendedCoordinateSequence(cloneCoordinates);
/*     */   }
/*     */   
/*     */   public int size() {
/* 189 */     return this.coordinates.length;
/*     */   }
/*     */   
/*     */   public Coordinate[] toCoordinateArray() {
/* 193 */     return (Coordinate[])this.coordinates;
/*     */   }
/*     */   
/*     */   public Envelope expandEnvelope(Envelope env) {
/* 198 */     for (int i = 0; i < this.coordinates.length; i++)
/* 199 */       env.expandToInclude(this.coordinates[i]); 
/* 201 */     return env;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 206 */     StringBuffer strBuf = new StringBuffer();
/* 207 */     strBuf.append("ExtendedCoordinateSequence [");
/* 208 */     for (int i = 0; i < this.coordinates.length; i++) {
/* 209 */       if (i > 0)
/* 209 */         strBuf.append(", "); 
/* 210 */       strBuf.append(this.coordinates[i]);
/*     */     } 
/* 212 */     strBuf.append("]");
/* 213 */     return strBuf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\geom\ExtendedCoordinateSequence.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */