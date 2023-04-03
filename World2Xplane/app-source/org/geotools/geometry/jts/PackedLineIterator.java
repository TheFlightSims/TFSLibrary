/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.impl.PackedCoordinateSequence;
/*     */ import java.awt.geom.AffineTransform;
/*     */ 
/*     */ public final class PackedLineIterator extends AbstractLiteIterator {
/*     */   private AffineTransform at;
/*     */   
/*  46 */   private PackedCoordinateSequence.Double coordinates = null;
/*     */   
/*  49 */   private int currentCoord = 0;
/*     */   
/*  52 */   private float oldX = Float.NaN;
/*     */   
/*  53 */   private float oldY = Float.NaN;
/*     */   
/*     */   private boolean done = false;
/*     */   
/*     */   private boolean isClosed;
/*     */   
/*     */   private boolean generalize = false;
/*     */   
/*  65 */   private float maxDistance = 1.0F;
/*     */   
/*     */   private float xScale;
/*     */   
/*     */   private float yScale;
/*     */   
/*     */   private int coordinateCount;
/*     */   
/*     */   public PackedLineIterator(LineString ls, AffineTransform at, boolean generalize, float maxDistance) {
/*  83 */     if (at == null)
/*  84 */       at = new AffineTransform(); 
/*  87 */     this.at = at;
/*  88 */     this.xScale = (float)Math.sqrt(at.getScaleX() * at.getScaleX() + at.getShearX() * at.getShearX());
/*  91 */     this.yScale = (float)Math.sqrt(at.getScaleY() * at.getScaleY() + at.getShearY() * at.getShearY());
/*  95 */     this.coordinates = (PackedCoordinateSequence.Double)ls.getCoordinateSequence();
/*  96 */     this.coordinateCount = this.coordinates.size();
/*  97 */     this.isClosed = ls instanceof com.vividsolutions.jts.geom.LinearRing;
/*  99 */     this.generalize = generalize;
/* 100 */     this.maxDistance = maxDistance;
/*     */   }
/*     */   
/*     */   public void setMaxDistance(float distance) {
/* 138 */     this.maxDistance = distance;
/*     */   }
/*     */   
/*     */   public double getMaxDistance() {
/* 148 */     return this.maxDistance;
/*     */   }
/*     */   
/*     */   public int currentSegment(float[] coords) {
/* 172 */     if (this.currentCoord == 0) {
/* 173 */       coords[0] = (float)this.coordinates.getX(0);
/* 174 */       coords[1] = (float)this.coordinates.getY(0);
/* 175 */       this.at.transform(coords, 0, coords, 0, 1);
/* 177 */       return 0;
/*     */     } 
/* 178 */     if (this.currentCoord == this.coordinateCount && this.isClosed)
/* 179 */       return 4; 
/* 181 */     coords[0] = (float)this.coordinates.getX(this.currentCoord);
/* 182 */     coords[1] = (float)this.coordinates.getY(this.currentCoord);
/* 183 */     this.at.transform(coords, 0, coords, 0, 1);
/* 185 */     return 1;
/*     */   }
/*     */   
/*     */   public int getWindingRule() {
/* 227 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 237 */     return this.done;
/*     */   }
/*     */   
/*     */   public void next() {
/* 246 */     if ((this.currentCoord == this.coordinateCount - 1 && !this.isClosed) || (this.currentCoord == this.coordinateCount && this.isClosed)) {
/* 249 */       this.done = true;
/* 251 */     } else if (this.generalize) {
/* 252 */       if (Float.isNaN(this.oldX)) {
/* 253 */         this.currentCoord++;
/* 254 */         this.oldX = (float)this.coordinates.getX(this.currentCoord);
/* 255 */         this.oldY = (float)this.coordinates.getY(this.currentCoord);
/*     */       } else {
/* 257 */         float distx = 0.0F;
/* 258 */         float disty = 0.0F;
/* 259 */         float x = 0.0F;
/* 260 */         float y = 0.0F;
/*     */         do {
/* 263 */           this.currentCoord++;
/* 264 */           x = (float)this.coordinates.getX(this.currentCoord);
/* 265 */           y = (float)this.coordinates.getY(this.currentCoord);
/* 267 */           if (this.currentCoord >= this.coordinateCount)
/*     */             continue; 
/* 268 */           distx = Math.abs(x - this.oldX);
/* 270 */           disty = Math.abs(y - this.oldY);
/* 276 */         } while (distx * this.xScale < this.maxDistance && disty * this.yScale < this.maxDistance && ((!this.isClosed && this.currentCoord < this.coordinateCount - 1) || (this.isClosed && this.currentCoord < this.coordinateCount)));
/* 280 */         if (this.currentCoord < this.coordinateCount) {
/* 281 */           this.oldX = x;
/* 282 */           this.oldY = y;
/*     */         } else {
/* 284 */           this.oldX = Float.NaN;
/* 285 */           this.oldY = Float.NaN;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 289 */       this.currentCoord++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int currentSegment(double[] coords) {
/* 298 */     System.out.println("Double!");
/* 299 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\PackedLineIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */