/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.awt.geom.AffineTransform;
/*     */ 
/*     */ public final class PolygonIterator extends AbstractLiteIterator {
/*     */   private AffineTransform at;
/*     */   
/*     */   private LineString[] rings;
/*     */   
/*  50 */   private int currentRing = 0;
/*     */   
/*  53 */   private int currentCoord = 0;
/*     */   
/*  56 */   private CoordinateSequence coords = null;
/*     */   
/*  59 */   private Coordinate oldCoord = null;
/*     */   
/*     */   private boolean done = false;
/*     */   
/*     */   private boolean generalize = false;
/*     */   
/*  68 */   private double maxDistance = 1.0D;
/*     */   
/*     */   private double xScale;
/*     */   
/*     */   private double yScale;
/*     */   
/*     */   public PolygonIterator(Polygon p, AffineTransform at) {
/*  83 */     int numInteriorRings = p.getNumInteriorRing();
/*  84 */     this.rings = new LineString[numInteriorRings + 1];
/*  85 */     this.rings[0] = p.getExteriorRing();
/*  87 */     for (int i = 0; i < numInteriorRings; i++)
/*  88 */       this.rings[i + 1] = p.getInteriorRingN(i); 
/*  91 */     if (at == null)
/*  92 */       at = new AffineTransform(); 
/*  95 */     this.at = at;
/*  96 */     this.xScale = Math.sqrt(at.getScaleX() * at.getScaleX() + at.getShearX() * at.getShearX());
/*  98 */     this.yScale = Math.sqrt(at.getScaleY() * at.getScaleY() + at.getShearY() * at.getShearY());
/* 101 */     this.coords = this.rings[0].getCoordinateSequence();
/*     */   }
/*     */   
/*     */   public PolygonIterator(Polygon p, AffineTransform at, boolean generalize) {
/* 112 */     this(p, at);
/* 113 */     this.generalize = generalize;
/*     */   }
/*     */   
/*     */   public PolygonIterator(Polygon p, AffineTransform at, boolean generalize, double maxDistance) {
/* 127 */     this(p, at, generalize);
/* 128 */     this.maxDistance = maxDistance;
/*     */   }
/*     */   
/*     */   public void setMaxDistance(double distance) {
/* 138 */     this.maxDistance = distance;
/*     */   }
/*     */   
/*     */   public double getMaxDistance() {
/* 148 */     return this.maxDistance;
/*     */   }
/*     */   
/*     */   public int currentSegment(double[] coords) {
/* 174 */     if (this.currentCoord == this.coords.size())
/* 175 */       return 4; 
/* 176 */     if (this.currentCoord == 0) {
/* 177 */       coords[0] = this.coords.getX(0);
/* 178 */       coords[1] = this.coords.getY(0);
/* 179 */       transform(coords, 0, coords, 0, 1);
/* 181 */       return 0;
/*     */     } 
/* 183 */     coords[0] = this.coords.getX(this.currentCoord);
/* 184 */     coords[1] = this.coords.getY(this.currentCoord);
/* 185 */     transform(coords, 0, coords, 0, 1);
/* 187 */     return 1;
/*     */   }
/*     */   
/*     */   protected void transform(double[] src, int index, double[] dest, int destIndex, int numPoints) {
/* 192 */     this.at.transform(src, index, dest, destIndex, numPoints);
/*     */   }
/*     */   
/*     */   public int getWindingRule() {
/* 201 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 211 */     return this.done;
/*     */   }
/*     */   
/*     */   public void next() {
/* 220 */     if (this.currentCoord == this.coords.size()) {
/* 221 */       if (this.currentRing < this.rings.length - 1) {
/* 222 */         this.currentCoord = 0;
/* 223 */         this.currentRing++;
/* 224 */         this.coords = this.rings[this.currentRing].getCoordinateSequence();
/*     */       } else {
/* 226 */         this.done = true;
/*     */       } 
/* 229 */     } else if (this.generalize) {
/* 230 */       if (this.oldCoord == null) {
/* 231 */         this.currentCoord++;
/* 232 */         this.oldCoord = this.coords.getCoordinate(this.currentCoord);
/*     */       } else {
/* 234 */         double distx = 0.0D;
/* 235 */         double disty = 0.0D;
/*     */         do {
/* 238 */           this.currentCoord++;
/* 240 */           if (this.currentCoord >= this.coords.size())
/*     */             continue; 
/* 241 */           distx = Math.abs(this.coords.getX(this.currentCoord) - this.oldCoord.x);
/* 243 */           disty = Math.abs(this.coords.getY(this.currentCoord) - this.oldCoord.y);
/* 248 */         } while (distx * this.xScale < this.maxDistance && disty * this.yScale < this.maxDistance && this.currentCoord < this.coords.size());
/* 250 */         if (this.currentCoord < this.coords.size()) {
/* 251 */           this.oldCoord = this.coords.getCoordinate(this.currentCoord);
/*     */         } else {
/* 253 */           this.oldCoord = null;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 257 */       this.currentCoord++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\PolygonIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */