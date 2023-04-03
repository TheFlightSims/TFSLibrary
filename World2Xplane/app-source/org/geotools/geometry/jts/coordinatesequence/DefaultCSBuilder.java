/*     */ package org.geotools.geometry.jts.coordinatesequence;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.DefaultCoordinateSequenceFactory;
/*     */ 
/*     */ public class DefaultCSBuilder implements CSBuilder {
/*     */   private Coordinate[] coordinateArray;
/*     */   
/*  37 */   private CoordinateSequenceFactory factory = (CoordinateSequenceFactory)DefaultCoordinateSequenceFactory.instance();
/*     */   
/*     */   public void start(int size, int dimensions) {
/*  43 */     this.coordinateArray = new Coordinate[size];
/*  44 */     for (int i = 0; i < size; i++)
/*  45 */       this.coordinateArray[i] = new Coordinate(); 
/*     */   }
/*     */   
/*     */   public CoordinateSequence end() {
/*  52 */     CoordinateSequence cs = this.factory.create(this.coordinateArray);
/*  53 */     this.coordinateArray = null;
/*  54 */     return cs;
/*     */   }
/*     */   
/*     */   public void setOrdinate(double value, int ordinateIndex, int coordinateIndex) {
/*  61 */     Coordinate c = this.coordinateArray[coordinateIndex];
/*  62 */     switch (ordinateIndex) {
/*     */       case 0:
/*  63 */         c.x = value;
/*     */         break;
/*     */       case 1:
/*  64 */         c.y = value;
/*     */         break;
/*     */       case 2:
/*  65 */         c.z = value;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getOrdinate(int ordinateIndex, int coordinateIndex) {
/*  73 */     Coordinate c = this.coordinateArray[coordinateIndex];
/*  74 */     switch (ordinateIndex) {
/*     */       case 0:
/*  75 */         return c.x;
/*     */       case 1:
/*  76 */         return c.y;
/*     */       case 2:
/*  77 */         return c.z;
/*     */     } 
/*  78 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  86 */     if (this.coordinateArray != null)
/*  87 */       return this.coordinateArray.length; 
/*  89 */     return -1;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/*  97 */     if (this.coordinateArray != null)
/*  98 */       return 2; 
/* 100 */     return -1;
/*     */   }
/*     */   
/*     */   public void setOrdinate(CoordinateSequence sequence, double value, int ordinateIndex, int coordinateIndex) {
/* 108 */     Coordinate c = sequence.getCoordinate(coordinateIndex);
/* 109 */     switch (ordinateIndex) {
/*     */       case 0:
/* 110 */         c.x = value;
/*     */         break;
/*     */       case 1:
/* 111 */         c.y = value;
/*     */         break;
/*     */       case 2:
/* 112 */         c.z = value;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\coordinatesequence\DefaultCSBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */