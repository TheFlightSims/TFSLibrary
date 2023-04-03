/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
/*     */ import com.vividsolutions.jts.io.WKTWriter;
/*     */ 
/*     */ public class BasicSegmentString implements SegmentString {
/*     */   private Coordinate[] pts;
/*     */   
/*     */   private Object data;
/*     */   
/*     */   public BasicSegmentString(Coordinate[] pts, Object data) {
/*  68 */     this.pts = pts;
/*  69 */     this.data = data;
/*     */   }
/*     */   
/*     */   public Object getData() {
/*  77 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setData(Object data) {
/*  84 */     this.data = data;
/*     */   }
/*     */   
/*     */   public int size() {
/*  86 */     return this.pts.length;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/*  87 */     return this.pts[i];
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/*  88 */     return this.pts;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/*  92 */     return this.pts[0].equals(this.pts[this.pts.length - 1]);
/*     */   }
/*     */   
/*     */   public int getSegmentOctant(int index) {
/* 104 */     if (index == this.pts.length - 1)
/* 104 */       return -1; 
/* 105 */     return Octant.octant(getCoordinate(index), getCoordinate(index + 1));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 110 */     return WKTWriter.toLineString((CoordinateSequence)new CoordinateArraySequence(this.pts));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\BasicSegmentString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */