/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import java.awt.geom.AffineTransform;
/*     */ 
/*     */ public final class PointIterator extends AbstractLiteIterator {
/*     */   private AffineTransform at;
/*     */   
/*     */   private Point point;
/*     */   
/*     */   private boolean done;
/*     */   
/*     */   private boolean moved;
/*     */   
/*     */   public PointIterator(Point point, AffineTransform at) {
/*  54 */     if (at == null)
/*  55 */       at = new AffineTransform(); 
/*  58 */     this.at = at;
/*  59 */     this.point = point;
/*  60 */     this.done = false;
/*  61 */     this.moved = false;
/*     */   }
/*     */   
/*     */   public int getWindingRule() {
/*  70 */     return 0;
/*     */   }
/*     */   
/*     */   public void next() {
/*  77 */     this.done = true;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/*  84 */     return (this.done && this.moved);
/*     */   }
/*     */   
/*     */   public int currentSegment(double[] coords) {
/*  91 */     if (!this.done && !this.moved) {
/*  92 */       coords[0] = this.point.getX();
/*  93 */       coords[1] = this.point.getY();
/*  94 */       this.at.transform(coords, 0, coords, 0, 1);
/*  96 */       return 0;
/*     */     } 
/*  98 */     coords[0] = this.point.getX();
/*  99 */     coords[1] = this.point.getY();
/* 100 */     this.at.transform(coords, 0, coords, 0, 1);
/* 102 */     this.moved = true;
/* 103 */     return 1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\PointIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */