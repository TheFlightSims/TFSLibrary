/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.util.CollectionUtil;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class ScaledNoder implements Noder {
/*     */   private Noder noder;
/*     */   
/*     */   private double scaleFactor;
/*     */   
/*     */   private double offsetX;
/*     */   
/*     */   private double offsetY;
/*     */   
/*     */   private boolean isScaled = false;
/*     */   
/*     */   public ScaledNoder(Noder noder, double scaleFactor) {
/*  65 */     this(noder, scaleFactor, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public ScaledNoder(Noder noder, double scaleFactor, double offsetX, double offsetY) {
/*  69 */     this.noder = noder;
/*  70 */     this.scaleFactor = scaleFactor;
/*  72 */     this.isScaled = !isIntegerPrecision();
/*     */   }
/*     */   
/*     */   public boolean isIntegerPrecision() {
/*  75 */     return (this.scaleFactor == 1.0D);
/*     */   }
/*     */   
/*     */   public Collection getNodedSubstrings() {
/*  79 */     Collection splitSS = this.noder.getNodedSubstrings();
/*  80 */     if (this.isScaled)
/*  80 */       rescale(splitSS); 
/*  81 */     return splitSS;
/*     */   }
/*     */   
/*     */   public void computeNodes(Collection inputSegStrings) {
/*  86 */     Collection intSegStrings = inputSegStrings;
/*  87 */     if (this.isScaled)
/*  88 */       intSegStrings = scale(inputSegStrings); 
/*  89 */     this.noder.computeNodes(intSegStrings);
/*     */   }
/*     */   
/*     */   private Collection scale(Collection segStrings) {
/*  95 */     return CollectionUtil.transform(segStrings, new CollectionUtil.Function() {
/*     */           public Object execute(Object obj) {
/*  98 */             SegmentString ss = (SegmentString)obj;
/*  99 */             return new NodedSegmentString(ScaledNoder.this.scale(ss.getCoordinates()), ss.getData());
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private Coordinate[] scale(Coordinate[] pts) {
/* 107 */     Coordinate[] roundPts = new Coordinate[pts.length];
/* 108 */     for (int i = 0; i < pts.length; i++)
/* 109 */       roundPts[i] = new Coordinate(Math.round(((pts[i]).x - this.offsetX) * this.scaleFactor), Math.round(((pts[i]).y - this.offsetY) * this.scaleFactor), (pts[i]).z); 
/* 115 */     Coordinate[] roundPtsNoDup = CoordinateArrays.removeRepeatedPoints(roundPts);
/* 116 */     return roundPtsNoDup;
/*     */   }
/*     */   
/*     */   private void rescale(Collection segStrings) {
/* 124 */     CollectionUtil.apply(segStrings, new CollectionUtil.Function() {
/*     */           public Object execute(Object obj) {
/* 127 */             SegmentString ss = (SegmentString)obj;
/* 128 */             ScaledNoder.this.rescale(ss.getCoordinates());
/* 129 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void rescale(Coordinate[] pts) {
/* 137 */     Coordinate p0 = null;
/* 138 */     Coordinate p1 = null;
/* 140 */     if (pts.length == 2) {
/* 141 */       p0 = new Coordinate(pts[0]);
/* 142 */       p1 = new Coordinate(pts[1]);
/*     */     } 
/* 145 */     for (int i = 0; i < pts.length; i++) {
/* 146 */       (pts[i]).x = (pts[i]).x / this.scaleFactor + this.offsetX;
/* 147 */       (pts[i]).y = (pts[i]).y / this.scaleFactor + this.offsetY;
/*     */     } 
/* 150 */     if (pts.length == 2 && pts[0].equals2D(pts[1]))
/* 151 */       System.out.println(pts); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\ScaledNoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */