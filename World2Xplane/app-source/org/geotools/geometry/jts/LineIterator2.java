/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.PathIterator;
/*     */ 
/*     */ public final class LineIterator2 implements PathIterator {
/*     */   private double[] allCoords;
/*     */   
/*     */   private AffineTransform at;
/*     */   
/*  46 */   private int currentCoord = 0;
/*     */   
/*     */   private int actualCoords;
/*     */   
/*     */   private boolean done = false;
/*     */   
/*     */   public int currentSegment(float[] coords) {
/*  64 */     if (this.currentCoord == 0) {
/*  66 */       coords[0] = (float)this.allCoords[0];
/*  67 */       coords[1] = (float)this.allCoords[1];
/*  68 */       if (this.at != null)
/*  69 */         this.at.transform(coords, 0, coords, 0, 1); 
/*  70 */       return 0;
/*     */     } 
/*  74 */     coords[0] = (float)this.allCoords[this.currentCoord * 2];
/*  75 */     coords[1] = (float)this.allCoords[this.currentCoord * 2 + 1];
/*  76 */     if (this.at != null)
/*  77 */       this.at.transform(coords, 0, coords, 0, 1); 
/*  78 */     return 1;
/*     */   }
/*     */   
/*     */   public void init(LineString ls, AffineTransform at) {
/*  91 */     if (at == null || at.isIdentity()) {
/*  93 */       this.at = null;
/*     */     } else {
/*  97 */       this.at = at;
/*     */     } 
/* 100 */     CoordinateSequence coordinates = ls.getCoordinateSequence();
/* 101 */     if (coordinates instanceof LiteCoordinateSequence) {
/* 104 */       this.allCoords = ((LiteCoordinateSequence)coordinates).getXYArray();
/* 105 */       this.actualCoords = coordinates.size();
/*     */     } else {
/* 110 */       this.actualCoords = coordinates.size();
/* 111 */       this.allCoords = new double[this.actualCoords * 2];
/* 112 */       for (int t = 0; t < this.actualCoords; t++) {
/* 114 */         this.allCoords[t * 2] = coordinates.getOrdinate(t, 0);
/* 115 */         this.allCoords[t * 2 + 1] = coordinates.getOrdinate(t, 1);
/*     */       } 
/*     */     } 
/* 119 */     this.done = false;
/* 120 */     this.currentCoord = 0;
/*     */   }
/*     */   
/*     */   public int getWindingRule() {
/* 138 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 148 */     return this.done;
/*     */   }
/*     */   
/*     */   public void next() {
/* 157 */     if (this.currentCoord == this.actualCoords - 1) {
/* 159 */       this.done = true;
/*     */     } else {
/* 163 */       this.currentCoord++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int currentSegment(double[] coords) {
/* 171 */     float[] fco = new float[6];
/* 172 */     int result = currentSegment(fco);
/* 173 */     coords[0] = fco[0];
/* 173 */     coords[1] = fco[1];
/* 174 */     coords[2] = fco[2];
/* 174 */     coords[3] = fco[3];
/* 175 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\LineIterator2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */