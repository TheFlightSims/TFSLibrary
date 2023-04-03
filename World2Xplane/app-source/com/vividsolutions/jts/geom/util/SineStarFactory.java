/*     */ package com.vividsolutions.jts.geom.util;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.util.GeometricShapeFactory;
/*     */ 
/*     */ public class SineStarFactory extends GeometricShapeFactory {
/*  51 */   protected int numArms = 8;
/*     */   
/*  52 */   protected double armLengthRatio = 0.5D;
/*     */   
/*     */   public SineStarFactory() {}
/*     */   
/*     */   public SineStarFactory(GeometryFactory geomFact) {
/*  73 */     super(geomFact);
/*     */   }
/*     */   
/*     */   public void setNumArms(int numArms) {
/*  83 */     this.numArms = numArms;
/*     */   }
/*     */   
/*     */   public void setArmLengthRatio(double armLengthRatio) {
/*  95 */     this.armLengthRatio = armLengthRatio;
/*     */   }
/*     */   
/*     */   public Geometry createSineStar() {
/* 105 */     Envelope env = this.dim.getEnvelope();
/* 106 */     double radius = env.getWidth() / 2.0D;
/* 108 */     double armRatio = this.armLengthRatio;
/* 109 */     if (armRatio < 0.0D)
/* 110 */       armRatio = 0.0D; 
/* 111 */     if (armRatio > 1.0D)
/* 112 */       armRatio = 1.0D; 
/* 114 */     double armMaxLen = armRatio * radius;
/* 115 */     double insideRadius = (1.0D - armRatio) * radius;
/* 117 */     double centreX = env.getMinX() + radius;
/* 118 */     double centreY = env.getMinY() + radius;
/* 120 */     Coordinate[] pts = new Coordinate[this.nPts + 1];
/* 121 */     int iPt = 0;
/* 122 */     for (int i = 0; i < this.nPts; i++) {
/* 124 */       double ptArcFrac = i / this.nPts * this.numArms;
/* 125 */       double armAngFrac = ptArcFrac - Math.floor(ptArcFrac);
/* 129 */       double armAng = 6.283185307179586D * armAngFrac;
/* 131 */       double armLenFrac = (Math.cos(armAng) + 1.0D) / 2.0D;
/* 134 */       double curveRadius = insideRadius + armMaxLen * armLenFrac;
/* 137 */       double ang = i * 6.283185307179586D / this.nPts;
/* 138 */       double x = curveRadius * Math.cos(ang) + centreX;
/* 139 */       double y = curveRadius * Math.sin(ang) + centreY;
/* 140 */       pts[iPt++] = coord(x, y);
/*     */     } 
/* 142 */     pts[iPt] = new Coordinate(pts[0]);
/* 144 */     LinearRing ring = this.geomFact.createLinearRing(pts);
/* 145 */     Polygon poly = this.geomFact.createPolygon(ring, null);
/* 146 */     return (Geometry)poly;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\SineStarFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */