/*     */ package com.vividsolutions.jts.operation.overlay.validate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.util.LinearComponentExtracter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class OffsetPointGenerator {
/*     */   private Geometry g;
/*     */   
/*     */   private boolean doLeft = true;
/*     */   
/*     */   private boolean doRight = true;
/*     */   
/*     */   public OffsetPointGenerator(Geometry g) {
/*  62 */     this.g = g;
/*     */   }
/*     */   
/*     */   public void setSidesToGenerate(boolean doLeft, boolean doRight) {
/*  73 */     this.doLeft = doLeft;
/*  74 */     this.doRight = doRight;
/*     */   }
/*     */   
/*     */   public List getPoints(double offsetDistance) {
/*  84 */     List offsetPts = new ArrayList();
/*  85 */     List lines = LinearComponentExtracter.getLines(this.g);
/*  86 */     for (Iterator<LineString> i = lines.iterator(); i.hasNext(); ) {
/*  87 */       LineString line = i.next();
/*  88 */       extractPoints(line, offsetDistance, offsetPts);
/*     */     } 
/*  91 */     return offsetPts;
/*     */   }
/*     */   
/*     */   private void extractPoints(LineString line, double offsetDistance, List offsetPts) {
/*  96 */     Coordinate[] pts = line.getCoordinates();
/*  97 */     for (int i = 0; i < pts.length - 1; i++)
/*  98 */       computeOffsetPoints(pts[i], pts[i + 1], offsetDistance, offsetPts); 
/*     */   }
/*     */   
/*     */   private void computeOffsetPoints(Coordinate p0, Coordinate p1, double offsetDistance, List<Coordinate> offsetPts) {
/* 112 */     double dx = p1.x - p0.x;
/* 113 */     double dy = p1.y - p0.y;
/* 114 */     double len = Math.sqrt(dx * dx + dy * dy);
/* 116 */     double ux = offsetDistance * dx / len;
/* 117 */     double uy = offsetDistance * dy / len;
/* 119 */     double midX = (p1.x + p0.x) / 2.0D;
/* 120 */     double midY = (p1.y + p0.y) / 2.0D;
/* 122 */     if (this.doLeft) {
/* 123 */       Coordinate offsetLeft = new Coordinate(midX - uy, midY + ux);
/* 124 */       offsetPts.add(offsetLeft);
/*     */     } 
/* 127 */     if (this.doRight) {
/* 128 */       Coordinate offsetRight = new Coordinate(midX + uy, midY - ux);
/* 129 */       offsetPts.add(offsetRight);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\validate\OffsetPointGenerator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */