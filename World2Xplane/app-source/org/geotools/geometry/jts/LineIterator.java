/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import java.awt.geom.AffineTransform;
/*     */ 
/*     */ public final class LineIterator extends AbstractLiteIterator {
/*     */   private AffineTransform at;
/*     */   
/*  50 */   private CoordinateSequence coordinates = null;
/*     */   
/*  53 */   private int currentCoord = 0;
/*     */   
/*  56 */   private float oldX = Float.NaN;
/*     */   
/*  57 */   private float oldY = Float.NaN;
/*     */   
/*     */   private boolean done = false;
/*     */   
/*     */   private boolean isClosed;
/*     */   
/*     */   private boolean generalize = false;
/*     */   
/*  69 */   private float maxDistance = 1.0F;
/*     */   
/*     */   private float xScale;
/*     */   
/*     */   private float yScale;
/*     */   
/*     */   private int coordinateCount;
/*     */   
/*  79 */   private static final AffineTransform NO_TRANSFORM = new AffineTransform();
/*     */   
/*     */   public LineIterator() {}
/*     */   
/*     */   public LineIterator(LineString ls, AffineTransform at, boolean generalize, float maxDistance) {
/*  95 */     init(ls, at, generalize, maxDistance);
/*     */   }
/*     */   
/*     */   public void init(LineString ls, AffineTransform at, boolean generalize, float maxDistance, float xScale, float yScale) {
/* 135 */     this.xScale = xScale;
/* 136 */     this.yScale = yScale;
/* 138 */     _init(ls, at, generalize, maxDistance);
/*     */   }
/*     */   
/*     */   public void init(LineString ls, AffineTransform at, boolean generalize, float maxDistance) {
/* 148 */     if (at == null)
/* 149 */       at = new AffineTransform(); 
/* 150 */     _init(ls, at, generalize, maxDistance);
/* 152 */     this.xScale = (float)Math.sqrt(at.getScaleX() * at.getScaleX() + at.getShearX() * at.getShearX());
/* 155 */     this.yScale = (float)Math.sqrt(at.getScaleY() * at.getScaleY() + at.getShearY() * at.getShearY());
/*     */   }
/*     */   
/*     */   private void _init(LineString ls, AffineTransform at, boolean generalize, float maxDistance) {
/* 169 */     if (at == null)
/* 170 */       at = NO_TRANSFORM; 
/* 173 */     this.at = at;
/* 174 */     this.coordinates = ls.getCoordinateSequence();
/* 175 */     this.coordinateCount = this.coordinates.size();
/* 176 */     this.isClosed = ls instanceof com.vividsolutions.jts.geom.LinearRing;
/* 178 */     this.generalize = generalize;
/* 179 */     this.maxDistance = maxDistance;
/* 180 */     this.done = false;
/* 181 */     this.currentCoord = 0;
/* 183 */     this.oldX = Float.NaN;
/* 184 */     this.oldY = Float.NaN;
/*     */   }
/*     */   
/*     */   public void setMaxDistance(float distance) {
/* 194 */     this.maxDistance = distance;
/*     */   }
/*     */   
/*     */   public double getMaxDistance() {
/* 204 */     return this.maxDistance;
/*     */   }
/*     */   
/*     */   public int getWindingRule() {
/* 283 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 293 */     return this.done;
/*     */   }
/*     */   
/*     */   public void next() {
/* 302 */     if ((this.currentCoord == this.coordinateCount - 1 && !this.isClosed) || (this.currentCoord == this.coordinateCount && this.isClosed)) {
/* 305 */       this.done = true;
/* 307 */     } else if (this.generalize) {
/* 308 */       if (Float.isNaN(this.oldX)) {
/* 309 */         this.currentCoord++;
/* 310 */         this.oldX = (float)this.coordinates.getX(this.currentCoord);
/* 311 */         this.oldY = (float)this.coordinates.getY(this.currentCoord);
/*     */       } else {
/* 313 */         float distx = 0.0F;
/* 314 */         float disty = 0.0F;
/* 315 */         float x = 0.0F;
/* 316 */         float y = 0.0F;
/*     */         do {
/* 319 */           this.currentCoord++;
/* 320 */           x = (float)this.coordinates.getX(this.currentCoord);
/* 321 */           y = (float)this.coordinates.getY(this.currentCoord);
/* 323 */           if (this.currentCoord >= this.coordinateCount)
/*     */             continue; 
/* 324 */           distx = Math.abs(x - this.oldX);
/* 326 */           disty = Math.abs(y - this.oldY);
/* 332 */         } while (distx * this.xScale < this.maxDistance && disty * this.yScale < this.maxDistance && ((!this.isClosed && this.currentCoord < this.coordinateCount - 1) || (this.isClosed && this.currentCoord < this.coordinateCount)));
/* 336 */         this.oldX = x;
/* 337 */         this.oldY = y;
/*     */       } 
/*     */     } else {
/* 340 */       this.currentCoord++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int currentSegment(double[] coords) {
/* 349 */     if (this.currentCoord == 0) {
/* 350 */       coords[0] = this.coordinates.getX(0);
/* 351 */       coords[1] = this.coordinates.getY(0);
/* 352 */       this.at.transform(coords, 0, coords, 0, 1);
/* 353 */       return 0;
/*     */     } 
/* 354 */     if (this.currentCoord == this.coordinateCount && this.isClosed)
/* 355 */       return 4; 
/* 357 */     coords[0] = this.coordinates.getX(this.currentCoord);
/* 358 */     coords[1] = this.coordinates.getY(this.currentCoord);
/* 359 */     this.at.transform(coords, 0, coords, 0, 1);
/* 361 */     return 1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\LineIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */